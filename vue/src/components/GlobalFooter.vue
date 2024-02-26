<template>
  <hr class="line-under-image" />
  <footer class="bg-#1d366f fixed-bottom mt-auto py-4">
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <div class="footer-column">
            <ul>
              <h6>Explore</h6>
              <li><router-link :to="{ name: 'itineraries' }">View Itineraries</router-link></li>
              <li><router-link :to="{ name: 'landmarks' }">View Landmarks</router-link></li>
              <li><router-link :to="{ name: 'safety' }">Safety & Waypoint</router-link></li>
              <li><router-link :to="{ name: 'faq' }">FAQ</router-link></li>
            
            </ul>
          </div>
        </div>
        <div class="col-md-4">
          <div class="footer-column">
            <ul>
              <h6>More</h6>
              <li><router-link :to="{ name: 'home' }">Home</router-link></li>
              <li><router-link :to="{ name: 'about' }">About Us</router-link></li>
              
              <h6 class="mt-2" v-if="isAdmin">Admin</h6>
              <li v-if="isAdmin"><router-link :to="{ name: 'landmarks' }">Add/Delete Landmarks</router-link></li>
            </ul>
          </div>
        </div>
        <div class="col-md-4">
          <div class="footer-column">
            <ul>
              <h6>Contact</h6>
              <span>Phone Number: 855-867-5309</span>
              <li>
                <span
                  >Email:
                  <a href="mailto:your@email.com"
                    >youshould@notemailus.com</a
                  ></span
                >
              </li>
              <span>Mailing Address: </span>
              <li>
                <p>
                  <span class="address-line">Waypoint, 123 Main Street</span
                  ><br />
                  <span class="address-line">Cincinnati, State, USA 45215</span
                  ><br />
                </p>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <span id="waypoint" class="text-white py-2 mt-4">&copy; 2023 Waypoint</span>
  </footer>
</template>

<script>


import MapOfLandmarks from "../components/MapOfLandmarks.vue";



export default {
  data() {
    return {
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
  components: MapOfLandmarks
}
};
</script>

<style scoped>


#waypoint {
  text-align: center;
  display: block;
  color: white;
  margin: 0 auto;
  font-size: 20px;
}

a {
  text-decoration: none;
  color: white;
  transition: color 0.3s ease;
}

a:hover {
  text-decoration: underline;
  color: #08c076;
}

ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

footer {
  margin-top: auto;
  position: relative;
  text-align: center;
  color: white;
  padding: 1rem;
}

h6 {
  color: #08c076;
}

span {
  color: white;
}



/* Media Queries for Mobile Design */

@media only screen and (max-width: 600px) {
  footer {
    padding: 20px;
  }
}
</style>
