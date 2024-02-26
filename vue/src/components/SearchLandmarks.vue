<template>
  <link rel="stylesheet" type="text/css" href="https://unpkg.com/@geoapify/geocoder-autocomplete@^1/styles/minimal.css">
  <div class="search-landmarks">
    <h1 class="search-landmarks-header">Search Landmarks</h1>
    <form @submit.prevent="sendStartingLocationCoordinates" class="search-form">
      <div ref="autocomplete" class="autocomplete-container autocomplete-input">
        <!-- <input
          type="text"
          class=""
          placeholder="Enter Location"
        /> -->
      </div>
      <div class="radius-container">
        <select v-model="searchRadius" class="radius-select">
          <option disabled value="">Search Radius</option>
          <option value="5">5 miles</option>
          <option value="10">10 miles</option>
          <option value="20">20 miles</option>
        </select>
        <button class="search-button">Search</button>
      </div>
    </form>
  </div>
</template>

<script>
import { GeocoderAutocomplete } from "@geoapify/geocoder-autocomplete";

export default {
  data() {
    return {
      searchTerm: "",
      searchRadius: 0,
      results: {},
    };
  },
  methods: {
    sendStartingLocationCoordinates() {
      //Implement logic for searching landmarks
      //Assuming logic cannot be built out until API is up and running on backend
      let startingLocationParameters = {
        startingLocationLong: this.results.lon,
        startingLocationLat: this.results.lat,
        searchRadius: this.searchRadius,
      };
      this.$emit("retrieveCoordinates", startingLocationParameters);
    },
  },
  mounted: function () {
    const autocomplete = new GeocoderAutocomplete(
      this.$refs.autocomplete,
      import.meta.env.VITE_GEOAPIFY_API_KEY,
      {
        /* Geocoder options */
      }
    );

    autocomplete.on("select", (location) => {
      // check selected location here
      this.results = location.properties;
    });

    autocomplete.on("suggestions", (suggestions) => {
      // process suggestions here
    });
  },
};
</script>

<style scoped>
/* Add component specific styles here */
.search-landmarks {
  text-align: center;
  margin: 20px;
  margin-top: 60px;
}

.autocomplete-container {
    color:black;
  }

.search-landmarks-header {
  font-family: Arial, sans-serif;
  color: #fcf5f5;
  /* font-size: 66px; */
  font-weight: bold;
  margin-bottom: 30px;
  /* text-transform: uppercase; */
  letter-spacing: 1px;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
}

.search-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border-radius: 8px;
  background-color: #f5f5f5;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.autocomplete-container {
  width: 100%;
  margin-bottom: 20px;
}

.autocomplete-input {
  width: calc(100% - 80px);
  padding: 10px;
  /* border-radius: 5px; */
  /* border: 1px solid #ccc; */
  font-size: 16px;
  margin-right: 10px;
}

.radius-container {
  display: flex;
  align-items: center;
}

.radius-select {
  flex: 1;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ccc;
  font-size: 16px;
  margin-right: 10px;
}

.search-button {
  padding: 12px 24px;
  border: none;
  border-radius: 5px;
  background-color: #4caf50;
  color: white;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.search-button:hover {
  background-color: #45a049;
}

.autocomplete-container {
  position: relative;
}

/* Mobile Styles */
@media screen and (max-width: 600px) {
  .search-landmarks{
  margin-top: 30px;
}

.search-landmarks-header{
  font-size: 36px;
  margin-bottom: 15px;
}

.search-form{
  max-width: 90%;
}

.autocomplete-input{
  width: calc(100% - 20px);
}

.radius-container{
  flex-direction: column;
}

.radius-select{
  width: 100%;
  margin-top: 10px;
}

.search-button{
  width: 100%;
  margin-top: 20px;
}

}
</style>
