<template>
<body>
    <global-header />
    <div class="itinerary-container">
        <h1 class="itinerary-info">{{ itinerary.itineraryId }}</h1>
        <p class="itinerary-info">{{ itinerary.userId }}</p>
        <p class="itinerary-name">{{ itinerary.itineraryName }}</p>
        <p class="itinerary-address">{{  itinerary.startingAddress }}</p>
        <div class="list-of-landmarks" v-for="landmark in itineraryLandmarks" v-bind:key="landmark.landmarkId">
            <router-link :to="{ name: 'landmark-details', params: {landmarkId: landmark.landmarkId} }">
                <p>{{ landmark.landmarkName }}</p>
            </router-link>
        </div>
    
        <div class="map-container" ref="myMap"></div>

        <div class="buttons-container">
        <button class="update-button" v-if="!showUpdateForm && !showDeleteNotification" @click="flipUpdateForm">Update</button>
        <form class="itinerary-form" v-if="showUpdateForm" @submit.prevent="updateItinerary">
                <div class="form-field">
                    <label for="itinerary-name-input" id="itinerary-name" class="input-label">Itinerary Name:</label>
                    <input v-model="itinerary.itineraryName" id="itinerary-name-input" class="text-input" type="text" placeholder="Enter itinerary name" />
                </div>
                <div class="form-field">
                    <label for="starting-address-input" id="itinerary-address" class="input-label">Starting Address:</label>
                    <div class="bottom-input">
                        <input v-model="itinerary.startingAddress" id="starting-address-input" class="text-input" type="text" placeholder="Enter starting address" />
                    </div>
                </div>
                <ul class="landmarks-list">
                    <li v-for="landmark in $store.state.landmarks" :key="landmark.landmarkId">
                        <label>
                            <input type="checkbox" @click="updateIds(landmark.landmarkId)" class="landmark-checkbox"/>
                            {{ landmark.landmarkName }}
                        </label>
                    </li>
                </ul>
                <div class="form-buttons">
                    <div class="button-wrapper">
                        <button type="submit" class="submit-button" @click="flipUpdateForm">Submit</button>
                    </div>
                    <div class="button-wrapper">
                        <button type="button" class="cancel-button" @click="flipUpdateForm">Cancel</button>
                    </div>
                </div>
            </form>

        <button class="delete-button" v-if="!showDeleteNotification && !showUpdateForm" @click="flipDeleteAlert">Delete</button>
        <container class="delete-box" v-if="showDeleteNotification">
            <h6>Are you sure you want to delete this itinerary?</h6>
            <div class="delete-buttons">
                <button class="delete-confirmation" @click="deleteItinerary">Yes!</button>
                <button class="delete-rejection" @click="flipDeleteAlert">No!</button>
            </div>
        </container>
    </div>
    </div>
    <global-footer />
</body>
</template>

<script>
import GlobalFooter from '../components/GlobalFooter.vue';
import GlobalHeader from '../components/GlobalHeader.vue';
import itineraryService from '../services/ItineraryService';
import geoApifyService from '../services/GeoApifyService';
import maplibre from 'maplibre-gl';

export default {
    data() {
        return {
            itinerary: {},
            itineraryStartingCoordinates: {},
            landmarkIdsToAdd: [],
            showUpdateForm: false,
            showDeleteNotification: false,
            itineraryLandmarks: [],
            itineraryRoute: {},
            itineraryWaypointsCoordinates: []
        }
    },
    components: {
        GlobalFooter,
        GlobalHeader
    }, 
    methods: {
        getItinerary() {
            this.itinerary = this.$store.state.itineraries.find(itinerary => itinerary.itineraryId == this.$route.params.itineraryId)
        },
        flipUpdateForm() {
            this.showUpdateForm = !this.showUpdateForm
        },
        updateIds(landmarkId) {
            console.log(landmarkId)
            if (this.landmarkIdsToAdd.includes(landmarkId) === true) {
                this.landmarkIdsToAdd = this.landmarkIdsToAdd.filter(id => id != landmarkId)
                console.log(this.landmarkIdsToAdd)
            } else {
                this.landmarkIdsToAdd.push(landmarkId)
                console.log(this.landmarkIdsToAdd)
            }
        },
        updateItinerary() {
            itineraryService.updateItineraryDetails(this.itinerary.itineraryId, this.itinerary)
            let idsToSend = this.landmarkIdsToAdd
            for(let landmark of this.itineraryLandmarks) {
                if(!idsToSend.includes(landmark.landmarkId)) {
                    idsToSend.push(landmark.landmarkId)
                }
            }
            itineraryService.editLandmarksForItinerary(this.itinerary.itineraryId, idsToSend)
            .then(response => {
                this.landmarkIdsToAdd = [];
            })
            .catch(error => {
                console.log(error);
            })
        },
        getItineraryLandmarks() {
            itineraryService.getLandmarksByItinerary(this.itinerary.itineraryId)
            .then(response => {
                this.itineraryLandmarks = response.data;
            })
            .catch(error => {
                console.log(error);
            });
        },
        flipDeleteAlert() {
            this.showDeleteNotification = !this.showDeleteNotification
            this.showUpdateForm = false;
        },
        deleteItinerary() {
            itineraryService.deleteItinerary(this.itinerary.itineraryId)
            this.$router.push('/itineraries')
        },
        getStartingAddressCoordinates() {
           geoApifyService.getCoordinatesForAddress(this.itinerary.startingAddress)
           .then(response => {
            this.itineraryStartingCoordinates.lat = response.data.results[0].lat;
            this.itineraryStartingCoordinates.lon = response.data.results[0].lon;

            this.getItineraryRoute();
           })
           .catch(error => {
            console.log([error, 'Could not retrieve route.']);
           });
        },
        getItineraryRoute() {
            let waypointsCoordinatesArray = [];

            waypointsCoordinatesArray.push({latitude: this.itineraryStartingCoordinates.lat , longitude: this.itineraryStartingCoordinates.lon});

            for (let landmark of this.itineraryLandmarks) {
                waypointsCoordinatesArray.push({latitude: landmark.landmarkLatitude , longitude: landmark.landmarkLongitude})
            }

            this.itineraryWaypointsCoordinates = waypointsCoordinatesArray;

            geoApifyService.getItineraryRoute(waypointsCoordinatesArray)
            .then(response => {
                this.itineraryRoute = response.data;

                this.createItineraryMap();
            })
            .catch(error => {
                console.log(error);
            });
        },
        createItineraryMap() {

            const mapStyle = 'https://maps.geoapify.com/v1/styles/osm-carto/style.json';

            const initialState = {
                lng: this.itineraryStartingCoordinates.lon,
                lat: this.itineraryStartingCoordinates.lat,
                zoom: 12
            };

            const map = new maplibre.Map({
                container: this.$refs.myMap,
                style: `${mapStyle}?apiKey=${import.meta.env.VITE_GEOAPIFY_API_KEY}`,
                center: [initialState.lng, initialState.lat],
                zoom: initialState.zoom
            });

            const markerPopup = new maplibre.Popup().setText('Some marker');
            new maplibre.Marker().setLngLat([initialState.lng, initialState.lat]).setPopup(markerPopup).addTo(map);
            

            

            setTimeout(() => {
                map.on('load', visualizeRoute(this.itineraryRoute.features[0], this.itineraryWaypointsCoordinates));
            }, 5000);


            function visualizeRoute(routeGeojson, waypointsCoordinates) {

                map.addSource('my-route', {
                    type:"geojson",
                    data: routeGeojson // <= add data here!
                });

                map.addLayer({
                    id: 'my-route-layer',
                    source: 'my-route',
                    type: 'line',
                    layout: {
                        'line-cap': "round",
                        'line-join': "round"
                    },
                    paint: {
                        'line-color': "#6084eb",
                        'line-width': 4,
                    }
                });
            }
        },
    },
    created() {
        this.getItinerary();
        this.getStartingAddressCoordinates();
        this.getItineraryLandmarks();
    }
}

</script>

<style scoped>

/* Import Maplibre styles */

@import '../../node_modules/maplibre-gl/dist/maplibre-gl.css';

body{
    background-color: #1d366f;
}
.map-container {
    height: 400px;
    width: 600px;
    margin: 20px auto;
    background-color: #345995;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
    border: 1px solid rgb(236, 173, 231);
    color: #E2ECF7;
}
.itinerary-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    background-color: #345995;
    border-radius: 10px;
    padding: 20px;
    margin: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
    border: 1px solid rgb(236, 173, 231);
    color: #E2ECF7;
    max-width: 700px;
    width: 100%;
    margin: 0 auto;
    margin-top: 50px;
}

.itinerary-name {
    font-size: 32px;
    color: #FFFFFF;
    margin-bottom: 15px;
    text-align: center;
    font-family: "Roboto", sans-serif;
    text-shadow: 3px 2px 4px rgba(0, 0, 0, 0.9);
}

.itinerary-info {
    font-size: 18px;
    color: #FFFFFF;
    margin-bottom: 10px;
    text-align: center;
    opacity: 0.7;
    display: none;
}

.itinerary-address {
    font-size: 20px;
    color: #FFFFFF;
    font-style: italic;
    text-align: center;
    opacity: 0.9;
    color: rgb(253, 243, 229);
    text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.9);
}

.buttons-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    align-items: center;
    margin-top: 20px;
}

button {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    color: #FFFFFF;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.itinerary-form{
    max-width: 400px;
    margin: 0 auto;
}

.form-field{
    margin-bottom: 20px ;
}

.input-label{
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}

.text-input{
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 14px;
}

.bottom-input{
    border: 1px solid #ccc;
    border-radius: 4px;
    overflow: hidden;
}

.form-buttons{
    display: flex;
    justify-content: center;
}

.button-wrapper{
    margin: 0 30px;
}

.submit-button, .cancel-button{
    border: 1px solid transparent;
    font-weight: bold;
    margin-top: 20px;
}

.submit-button{
    background-color: #337AB7;
}

.cancel-button{
    background-color: #F44336;
}

.update-button {
    background-color: #4CAF50;
}
.delete-button {
    background-color: #F44336;
    margin-left: 50px;
}
.confirmation-button {
    background-color: #3498DB;
}
.rejection-button {
    background-color: #FFA500;
}
h6 {
    margin-left: 10px;
}
button:hover {
    opacity: 0.9;
}

/* DELETE SECTION */
.delete-box {
    background-color: #fff;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
    padding: 20px;
    margin-top: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 0 auto;
}
.delete-box h6 {
    font-size: 18px;
    color: #333;
    margin-bottom: 15px;
}
.delete-box button {
    padding: 8px 16px;
    margin-right: 10px;
    border-radius: 3px;
    border: none;
    cursor: pointer;
}
.delete-box .delete-confirmation {
    background-color: #D9534F;
    color: #fff;
}
.delete-box .delete-rejection {
    background-color: #337AB7;
    color: #fff;
}


</style>

