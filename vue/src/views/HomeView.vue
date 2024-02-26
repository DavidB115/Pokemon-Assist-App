<template>
  <div class="home">
    <global-header />
    <img
      class="homeview-image fade-in"
      src="src\assets\img\logo-large.png"
      @load="handleImageload"
    />
   
    <h2 class="homeview-header">
      <div class="word-group">
        <span>UNCOVER,</span> <span>PLAN, </span>
        <span class="mb-5"> EXPERIENCE. </span>
        <!-- <span>and </span>
        <span>adventure </span>
        <span>on</span> -->
        <!-- <hr class="line-under-image mt-5 mb-5" /> -->
      </div>
    </h2>
            
    <!-- <div class="full-width-div" style="background-color: #2e52a4">
      <div class="container" id="sh-container" >
      <img 
        src="src\assets\img\super-hero.png"
        alt="super-hero"
        class="rounded img-fluid height:auto mx-auto d-block image"
        >
        <div class="overlay-text">
          <h4>Feel like a super hero</h4>
          <span>Use our travel guide and take off.</span>
        
        </div>
        </div>
    </div> -->
      




      <div class="container mt-5">
        
        <div class="d-flex flex-col align-items-center justify-content-center mb-5">
          <div class="p-2">
            <router-link v-bind:to="{ name: 'itineraries' }">
            <img
              src="src\assets\img\itinerary-logo.png"
              alt="itineraries"
              class="rounded img-fluid"
              id="itinerary-photo"
            />
          </router-link>
          </div>
          <div class="col-md-6">
            <h4 class=" mb-1">Build an itinerary and share with family and friends:</h4>
            <p>
            Create your personalized itinerary effortlessly and share it with friends.
            
          </p>
          </div>
        </div>
        
        <div class="d-flex flex-col align-items-center justify-content-center mb-5">
          <div class="p-2">
            <router-link v-bind:to="{ name: 'landmarks' }"
          >
            <img
              src="src\assets\img\landmarks-logo.png"
              alt="landmarks"
              class="rounded img-fluid"
              id="landmarks-photo"
            />
          </router-link>
          </div>
          <div class="col-md-6">
            <h4 class="mb-1">Look at suggested landmarks around Cincinnati:</h4>
            <p class="mb-0 text-md-lg">
              Discover the must-see landmarks and make the most of your Cincinnati
            visit!
          </p>
          </div>
        </div>
      </div>
      <div style="background-color: #08c076" class="">
      <h3 class="ml-5 pl-5 mt-5">Meet the team</h3>
      <p class="ml-5 pl-5 pb-3">Meet the brilliant minds behind our success! Our dedicated team brings 
        diverse talents and expertise to the table, working collaboratively 
        to turn ideas into reality. With passion as our driving force, 
        we strive for excellence in every endeavor. Get to know the faces 
        behind the brand and discover the unique skills and perspectives 
        that make our team an unstoppable force. Together, 
        we're not just a team; we're a family, united by a shared 
        commitment to innovation and success.</p>

        <router-link class="rounded-button" :to="{ name: 'about' }">About Us</router-link> 
      </div>
</div>
    <global-footer />
  
</template>

<script>
import GlobalHeader from "../components/GlobalHeader.vue";
import GlobalFooter from "../components/GlobalFooter.vue";
import landmarkService from "../services/LandmarkService";

export default {
  components: {
    GlobalHeader,
    GlobalFooter,
  },
  methods: {
    handleImageload() {
      const image = document.querySelector(".homeview-image");
      image.classList.add("loaded");
    },
    getLandmarks() {
      landmarkService.getAllLandmarks()
        .then((response) => {
          this.$store.commit('SET_LANDMARKS', response.data);
        })
        .catch(error => {
          console.log(error)
        });
    }
  },
  mounted() {
    window.initAOS();
  },
  created() {
    this.getLandmarks();
  }
};
</script>

<style scoped>
.homeview-header {
  text-align: center;
  /* margin-top: 50px; */
  font-family: "Roboto", sans-serif;
  font-size: 50px;
  color: #08c076;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
  letter-spacing: 2px;
}

.homeview-image {
  display: block;
  margin: auto;
  margin-top: 5%;
  border-radius: 30px;
}

.sh-container {
  position: relative;
}

.fade-in {
  opacity: 0;
  transition: opacity 2.5s ease-in-out;
}

.fade-in.loaded {
  opacity: 1;
}

.overlay-text {
  position: absolute;
  bottom: 0;
  left: 0;
  padding: 10;
  z-index: 1;
  
}

.container {
  position: relative;
  width: 100%;
}

.rounded-button {
  display: inline-block;
  padding: 10px 20px; /* Adjust padding as needed */
  font-size: 16px; /* Adjust font size as needed */
  text-align: center;
  text-decoration: none;
  border: none;
  border-radius: 20px; /* Adjust border radius for roundness */
  background-color: #ffffff; /* Button background color */
  color: #000000; /* Button text color */
  cursor: pointer;

  margin-left: 6%

}

/* Styles for itinerary and graphic */

/* .image-with-paragraphs {
  display: flex;
  justify-content: space-between;
  margin-top: 40px;
} */

/* .image-container {
  display: flex;
  flex-direction: row;
  padding: 20px;
} */

img {
  max-width: 100%;
  height: auto;
  display: block;
}

#itinerary-photo {
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
}

#landmarks-photo {
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
}

/* .gif-container {
  display: flex;
  justify-content: center;
  margin-top: 100px;
  height: 70vh;
  border-radius: 10px;
}

.gif-container img {
  max-width: 100%;
  height: auto;
  border: 2px solid rgb(232, 228, 229);
  border-radius: 10px;
} */

/* p {
  font-size: 1.2rem;
  max-width: 300px;
  color: white;
  margin: 0 auto;
  line-height: 1.6;
  font-family: "Roboto", sans-serif;
} */

@keyframes scale {
  100% {
    transform: scale(1);
  }
}

.word-group span {
  margin-right: 5px;
}

span {
  display: inline-block;
  opacity: 0;
  filter: blur(4px);
}

span:nth-child(1) {
  animation: fade-in 0.8s 0.1s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

span:nth-child(2) {
  animation: fade-in 0.8s 0.2s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

span:nth-child(3) {
  animation: fade-in 0.8s 0.3s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

span:nth-child(4) {
  animation: fade-in 0.8s 0.4s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

span:nth-child(5) {
  animation: fade-in 0.8s 0.5s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

span:nth-child(6) {
  animation: fade-in 0.8s 0.6s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

span:nth-child(7) {
  animation: fade-in 0.8s 0.7s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

span:nth-child(8) {
  animation: fade-in 0.8s 0.8s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

span:nth-child(9) {
  animation: fade-in 0.8s 0.9s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

span:nth-child(10) {
  animation: fade-in 0.8s 1s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

span:nth-child(11) {
  animation: fade-in 0.8s 1.1s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

span:nth-child(12) {
  animation: fade-in 0.8s 1.2s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

span:nth-child(13) {
  animation: fade-in 0.8s 1.3s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

span:nth-child(14) {
  animation: fade-in 0.8s 1.4s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

span:nth-child(15) {
  animation: fade-in 0.8s 1.5s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

span:nth-child(16) {
  animation: fade-in 0.8s 1.6s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

span:nth-child(17) {
  animation: fade-in 0.8s 1.7s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

span:nth-child(18) {
  animation: fade-in 0.8s 1.8s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

@keyframes fade-in {
  100% {
    opacity: 1;
    filter: blur(0);
  }
}

/* Media Queries for Mobile */
@media only screen and (max-width: 600px) {
  .homeview-header {
    font-size: 20px;
  }

  .homeview-image {
    width: 100%;
    margin-top: 20px;
  }

  .line-under-image {
    width: 60%;
  }

  .image-with-paragraphs {
    flex-direction: column;
    align-items: center;
    margin-top: 20px;
  }

  .image-container {
    flex-direction: column;
    align-items: center;
    padding: 10px;
  }

  /* #itinerary-photo {
    margin-bottom: 40px;
  } */

  p {
    max-width: 80%;
    text-align: center;
    margin-top: 20px;
  }
  .gif-container {
    height: 50vh;
    margin-left: 30px;
    margin-right: 30px;
  }
}

/* Media Queries for Larger Screens */
@media only screen and (min-width: 601px) {
  #landmarks-photo {
    /* max-width: none; */
  }
}
</style>
