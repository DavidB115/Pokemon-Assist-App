<template>
  <h1 class="text-center bg-primary mt-5">Add a Landmark</h1>
      <form class="login-form" v-on:submit.prevent="submitForm">
          <div class="form-group ml-5">
              <label for="landmarkName">Landmark Name: </label>
              <input type="text" class="form-control" v-model="landmark.landmarkName" id="landmarkName" placeholder="Enter landmark name" required>
              <label for="landmarkAddress" class="mt-2">Landmark Address: </label>
              <div ref="autocomplete" class="autocomplete-container autocomplete-input"></div>
              <label for="landmarkDetails" class="mt-2">Landmark Details: </label>
              <textarea class="form-control" v-model="landmark.landmarkDetails" id="landmarkDetails" placeholder="Enter landmark details" rows="2" required></textarea>
              <button type="submit" class="btn btn-primary mr-3 mt-3">Submit</button>
              <button type="reset" class="btn btn-secondary mt-3">Reset</button>
          </div>
      </form>
  </template>
  
  
  <script>
  
  import { GeocoderAutocomplete } from "@geoapify/geocoder-autocomplete";
  import LandmarkService from "../services/LandmarkService";
  
  
  
  export default {

      components : {
       
      },
  
      data() {
          return {
              landmark: {}
          }
      },
  
      methods: {
          submitForm() {
              
            LandmarkService.addLandmark(this.landmark)
            .catch((err) => {
              console.error(err);
            });
            this.$router.push({name: 'landmarks'})
          },
          handleErrorResponse(error, verb) {
            if (error.response) {
              this.$store.commit('SET_NOTIFICATION',
                "Error " + verb + " card. Response received was '" + error.response.statusText + "'.");
            } else if (error.request) {
              this.$store.commit('SET_NOTIFICATION', "Error " + verb + " landmark. Server could not be reached.");
            } else {
              this.$store.commit('SET_NOTIFICATION', "Error " + verb + " landmark. Request could not be created.");
            }
          },
          
      },
  
      mounted: function () {
      const autocomplete = new GeocoderAutocomplete(
        this.$refs.autocomplete,
        import.meta.env.VITE_GEOAPIFY_API_KEY,
        {
          country: 'US'
        }
      );
  
      autocomplete.on("select", (location) => {
        // check selected location here
        
        this.landmark.landmarkAddress = location.properties.formatted;
        this.landmark.landmarkLatitude = location.properties.lat;
        this.landmark.landmarkLongitude = location.properties.lon;
      });
  
      autocomplete.on("suggestions", (suggestions) => {
        // process suggestions here
      });
    },
  };
  </script>
  
  
  
  <style scoped>
  .login-form {
    display: flex;
    flex-direction: column;
    /* align-items: center; */
    /* margin: auto; */
    width: 520px;
    /* border-radius: 10px; */
    /* margin-top: 60px;
    margin-bottom: 90px; */
    padding: 20px;
  }
  .form-group {
    color: white;
  }
  </style>