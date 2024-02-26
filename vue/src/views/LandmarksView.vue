<template>
  <div class="landmarks-view">
  <global-header />
  <div>
    <search-landmarks @retrieveCoordinates="filterLandmarks($event)"/>
    <div class="reset-form">
      <input type="button" value="Reset Form" v-on:click="resetListOfLandmarks" class="reset-button" >
    </div> 
  </div>
  <div>
    <map-of-landmarks />
  </div>
  <div
    class="landmark-card-list"
    v-for="landmark in displayedLandmarks"
    v-bind:key="landmark.landmarkId"
  >
    <router-link v-bind:to="{name: 'landmark-details', params: {landmarkId: landmark.landmarkId}}">
      <landmark-card v-bind:landmark="landmark" />
    </router-link>
    

  </div>

  <admin v-if="isAdmin" />
  <global-footer />
  </div>
</template>


<script>
import GlobalHeader from "../components/GlobalHeader.vue";
import GlobalFooter from "../components/GlobalFooter.vue";
import SearchLandmarks from "../components/SearchLandmarks.vue";
import MapOfLandmarks from "../components/MapOfLandmarks.vue";
import LandmarkCard from "../components/LandmarkCard.vue";
import geoApifyService from "../services/GeoApifyService.js";
import Admin from "../components/admin.vue";
import axios from 'axios';

export default {
  components: {
    GlobalHeader,
    GlobalFooter,
    MapOfLandmarks,
    SearchLandmarks,
    LandmarkCard,
    Admin,
  },
  data() {
    return {
      displayedLandmarks: []
    };
  },

  computed: {
    isAdmin() {
      if (this.$store.state.user.authorities) {
        return this.$store.state.user.authorities[0].name === 'ROLE_ADMIN';
      } else {
        return false;
      }
    },
  },
  methods: {
    getLandmarks() {
          this.displayedLandmarks = this.$store.state.landmarks;
    },
    filterLandmarks(startingLocationParameters) {

      geoApifyService.getRoutes(startingLocationParameters, this.$store.state.landmarks)
      .then(
        axios.spread((...data) => {

          this. displayedLandmarks = this.displayedLandmarks.filter((landmark, index) => {

            console.log(data[index].data.features[0].properties.distance);
            console.log(startingLocationParameters.searchRadius);

            return data[index].data.features[0].properties.distance <= startingLocationParameters.searchRadius;
          })
      }))
      .catch(error => {
        this.handleErrorResponse();
      });
      
    },
    handleErrorResponse() {
      console.log('Error: Network Error');
    },
    resetListOfLandmarks() {
      this.displayedLandmarks = this.$store.state.landmarks;
    }
  },
  created() {
    this.getLandmarks();
  }
}
</script>

<style scoped>

.reset-button{
  display: block;
  margin: 0 auto;
  padding: 10px 30px;
  margin-bottom: 60px;
  background-color: #3498db;
  color: #fff;
  border: none;
  border-radius: 5px;
  font-weight: bold;
  cursor: pointer;
  border: 2px solid;
}

.reset-button:hover{
  background-color: #DC3545;
}

.landmark-card-list {
  display: flex;
  flex-direction: row;
  justify-content: center;
}


/* Remove if this doesn't work on mobile screens once mobile is set up */
.reset-form{
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 400px;
  margin: 0 auto;
  margin-top: -34px;
  padding: 10px;
  border-radius: 8px;
  background-color: #f5f5f5;
  margin-bottom: 30px;
}


@media screen and (max-width: 600px){
  .reset-form{
    max-width: 80.3%;
  }
}
</style>
