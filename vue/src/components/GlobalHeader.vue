<template>
  <!-- Bootstrap navbar -->
  <nav class="navbar navbar-expand-lg shadow 5">
    <div class="container-fluid">
      <router-link class="navbar-brand mx-auto" :to="{ name: 'home' }">
        <img src="@/assets/img/logo-small.png" alt="Logo" class="navbar-logo" />
      </router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNavDropdown"
        aria-controls="navbarNavDropdown"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav me-auto">
          <li class="nav-item">
            <router-link
              class="nav-link"
              :to="{ name: 'home' }"
              aria-current="page"
            >
              Home
            </router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'landmarks' }">
              View Landmarks
            </router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'itineraries' }">
              Itinerary
            </router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'about' }">
              About
            </router-link>
          </li>
          <li class="nav-item">
            <router-link
              v-if="!isLoggedIn"
              class="nav-link"
              :to="{ name: 'login' }"
            >
              Login
            </router-link>
            <router-link
              v-if="isLoggedIn"
              class="nav-link"
              :to="{ name: 'logout' }"
            >
              Logout
            </router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  computed: {
    isLoggedIn() {
      return !!this.$store.state.token;
    },
  },
  methods: {
    login() {
      this.$store.commit("SET_AUTH_TOKEN", "token");
      this.$store.commit("SET_USER", { username: "user" });
    },
    logout() {
      this.$store.commit("LOGOUT");
    },
  },
};
</script>

<style scoped>
.brand-text {
  font-size: 1.2em;
  color: #08c076;
  transition: color 0.3s ease-in-out;
}

.brand-text:hover {
  color: #345a9c;
}

.nav-link {
  font-weight: bold;
  color: white;
  transition: color 0.3s ease-in-out;
}

.nav-link:hover {
  color: #08c076;
}
/* .navbar-logo {
  max-height: 60px;
  border-radius: 50%;
  margin-left: 20px;
  border: 2px solid black;
} */

.navbar-toggler:hover {
  background-color: #4267b9;
}

/* Media Queries for Smaller Screens  */
@media only screen and (max-width: 600px) {
  .nav-link {
    text-align: center;
  }

  button {
    margin-right: 5px;
  }

  .brand-text {
    display: none;
  }

  .navbar-brand .brand-text {
    display: inline;
  }

  .city-tours-text {
    font-size: 20px;
    color: #4267b9;
    display: inline;
  }
}

/* Media Queries for Larger Screens */
@media only screen and (min-width: 601px) {
  .nav-link {
    margin-left: 25px;
  }

  button {
    margin-right: 25px;
  }
}
</style>
