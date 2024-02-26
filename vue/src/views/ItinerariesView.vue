<template>
    <global-header />
    <div class="itinerary-container">
        <h1 class="itinerary-header">Your Itineraries:</h1>
        <div class="itineraries-list">
            <div class="itinerary-item" v-for="itinerary in this.$store.state.itineraries" :key="itinerary.itineraryId">
                <itinerary-card :itinerary="itinerary" />
            </div>
        </div>
        <button class="new-itinerary-button" v-if="!showForm" @click="flipForm">Create New Itinerary</button>
        <form class="itinerary-form" v-if="showForm" @submit.prevent="createItinerary">
            <div class="form-field">
                <label for="itinerary-name-input" id="itinerary-name">Itinerary Name:</label>
                <input v-model="itinerary.itineraryName" id="itinerary-name-input" type="text" placeholder="Enter itinerary name" />
            </div>
            <div class="form-field">
                <label for="starting-address-input" id="itinerary-address">Starting Address:</label>
                <div class="bottom-input">
                    <input v-model="itinerary.startingAddress" id="starting-address-input" type="text" placeholder="Enter starting address" />
                </div>
            </div>
            <ul class="landmarks-list">
                <li v-for="landmark in $store.state.landmarks" :key="landmark.landmarkId">
                    <label>
                        <input type="checkbox" @click="updateIds(landmark.landmarkId)" class="landmark-checkbox" />
                        {{ landmark.landmarkName }}
                    </label>
                </li>
            </ul>
            <div class="form-buttons">
                <div class="button-wrapper">
                    <button type="submit" class="submit-button">Submit</button>
                </div>
                <div class="button-wrapper">
                    <button type="button" class="cancel-button" @click="flipForm">Cancel</button>
                </div>
            </div>
        </form>
    </div>
    <global-footer />
  </template>
  

<script>
import GlobalFooter from '../components/GlobalFooter.vue';
import GlobalHeader from '../components/GlobalHeader.vue';
import ItineraryCard from '../components/ItineraryCard.vue';
import itineraryService from '../services/ItineraryService.js';
//import { GeocoderAutocomplete } from "@geoapify/geocoder-autocomplete";


export default {
    components: {
    GlobalFooter,
    GlobalHeader,
    ItineraryCard

},
    data() {
        return {
            showForm: false,
            landmarkIds: [],
            itinerary: {
                userId: this.$store.state.user.id,
                itineraryName: "",
                startingAddress: ""
            },
            newItineraryId: 0
        }
    },
    methods: {
        flipForm() {
            this.showForm = !this.showForm
        },
        updateIds(landmarkId) {
            console.log(landmarkId)
            if (this.landmarkIds.includes(landmarkId) === true) {
                this.landmarkIds = this.landmarkIds.filter(id => id != landmarkId)
                
            } else {
                this.landmarkIds.push(landmarkId)
                
            }
        },
        getItineraries() {
            itineraryService.getAllItineraries(this.$store.state.user.id)
            .then(response => {
                this.$store.commit('SET_USER_ITINERARIES', response.data);
            })
            .catch(error => {
                console.log(error);
                this.handleErrorResponse();
            });
        },
        createItinerary() {
            itineraryService.createBaseItinerary(this.itinerary)
            .then(response => {
                this.newItineraryId = response.data.itineraryId
                //console.log(this.landmarkIds)
                itineraryService.createLandmarksForItinerary(this.newItineraryId, this.landmarkIds)

                this.landmarkIds = [];
                 this.itinerary = {
                userId: this.$store.state.user.userId,
                itineraryName: "",
                startingAddress: ""
                 };
            this.showForm = false;
            })
            .catch(error => {
                console.log(error)
            });
        },
        handleErrorResponse() {
            console.log('Error: Network Error');
        }
    },
    created() {
        this.getItineraries();
    },
//     mounted: function () {
//     const autocomplete = new GeocoderAutocomplete(
//       this.$refs.autocomplete,
//       import.meta.env.VITE_GEOAPIFY_API_KEY,
//       {
//         /* Geocoder options */
//       }
//     );

//     autocomplete.on("select", (location) => {
//       // check selected location here
//       this.results = location.properties;
//     });

//     autocomplete.on("suggestions", (suggestions) => {
//       // process suggestions here
//     });
//   }
}

</script>

<style scoped>

    .itinerary-header{
        text-align: center;
        margin-top: 30px;
        margin-bottom: 30px;
        font-family: "Roboto", sans-serif;
        font-size: 50px;
        color: #e2ecf7;
        text-shadow: 3px 2px 4px rgba(0, 0, 0, 0.9);
        letter-spacing: 2px;
    }
    .itinerary-container {
        max-width: 600px;
        margin: 0 auto;
        padding: 20px;
        font-family: 'Arial', sans-serif;
    }

    .itineraries-list{
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
    }
    #itinerary-name, #itinerary-address{
        font-size: 30px;
        color: #e2ecf7;
        text-shadow: 3px 2px 2px rgba(0, 0, 0, 0.9);
        letter-spacing: 1px;
    }

    .new-itinerary-button {
        background-color: #3498db;
        color: white;
        border: none;
        border-radius: 4px;
        padding: 8px 16px;
        cursor: pointer;
        display: block;
        margin: 0 auto;
        margin-top: 30px;
    }

    h1 {
        font-size: 24px;
        margin-bottom: 20px;
       }

    .itineraries-list {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
    }

    .new-itinerary-button {
        background-color: #3498db;
        color: white;
        border: none;
        border-radius: 4px;
        padding: 10px 20px;
        cursor: pointer;
        margin-bottom: 20px;
    }

    .itinerary-form {
        background-color: rgb(59, 108, 172);
        padding: 20px;
        border-radius: 8px;
        margin-top: 50px;
        margin-bottom: 50px;
        border: 1px solid rgb(236, 173, 231);
    }

    .form-field {
        margin-bottom: 15px;
    }

    label {
        font-weight: bold;
        margin-bottom: 5px;
        display: block;
        
    }

    input[type="text"] {
        width: calc(100% - 20px);
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    .bottom-input{
        margin-bottom: 30px;
    }

    .landmarks-list {
        list-style: none;
        padding: 0;
        margin: 0;
        margin-left: 20px;
        color: rgb(253, 243, 229);
        text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.9);
    }

    .landmark-checkbox {
        margin-right: 5px;
    }

    .form-buttons {
        display: flex;
        justify-content: center;
        margin-top: 30px;
        margin-bottom: 30px;
    }

    .button-wrapper{
        margin: 0 30px;
    }

    .submit-button {
        background-color: #4caf50;
        color: white;
        border: none;
        border-radius: 4px;
        padding: 10px 30px;
        cursor: pointer;
        font-weight: bold;
    }

    .submit-button:hover{
        background-color: #45a049;
    }

    .cancel-button {
        background-color: #3498db;
        color: white;
        border: none;
        border-radius: 4px;
        padding: 10px 30px;
        cursor: pointer;
        font-weight: bold;
    }

    .cancel-button:hover{
        background-color: #BB2D38;
    }

    /* Media styles for phone */
    @media screen and (max-width: 600){
    .landmarks-list{
        margin-left: none;
    }
   }
</style>