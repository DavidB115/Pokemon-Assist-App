<template>
  <h1 class="map-header">Use our map</h1>
  <!-- <img width="600" 
         height="400" 
         src="../../img/all-landmarks-static-map.jpg"
         alt="1725 Riverside Drive, Cincinnati, OH 45202, United States of America"
    /> -->
  <img
    width="600"
    height="400"
    :src="imgUrl"
    alt="1725 Riverside Drive, Cincinnati, OH 45202, United States of America"
  />
  <hr class="divider-header" />
</template>

<script>
export default {
  data() {
    return {
      imgUrl: "https://maps.geoapify.com/v1/staticmap?style=osm-carto&width=600&height=400&center=lonlat:-84.482038,39.116247&zoom=11.4604&marker="
    }
  },
  methods: {
    createImgUrl() {
      let coordinateParameters = "";


      for (let [index, landmark] of this.$store.state.landmarks.entries()) {
        coordinateParameters += ('lonlat:' + landmark.landmarkLongitude + ',' + landmark.landmarkLatitude + ';color:%23ff0000;size:small');
        
        if (index  !== this.$store.state.landmarks.length - 1) coordinateParameters += '|';
      }

      this.imgUrl += coordinateParameters + '&apiKey=' + import.meta.env.VITE_GEOAPIFY_API_KEY;
    }
  }, 
  created() {
    this.createImgUrl();
  }
};
</script>

<style scoped>
.map-header {
  font-family: Arial, sans-serif;
  color: #fcf5f5;
  /* font-size: 36px; */
  font-weight: bold;
  margin-bottom: 30px;
  /* text-transform: uppercase; */
  letter-spacing: 1px;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
  text-align: center;
}

img {
  display: block;
  margin: 0 auto;
  border-radius: 5px;
  border: 2px solid #ccc;
  margin-bottom: 50px;
}

.divider-header {
  width: 30%;
  margin: auto;
  border: 1px solid #f4f2f2;
  margin-bottom: 60px;
}
</style>
