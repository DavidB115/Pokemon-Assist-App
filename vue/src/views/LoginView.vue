<template>
  <div id="login">
    <global-header />
    <form v-on:submit.prevent="login" class="login-form">
      <h1 class="login-header">Sign In:</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username:</label>
        <input
          type="text"
          id="username"
          v-model="user.username"
          required
          class="styled-input"
          autofocus
        />
      </div>
      <div class="form-input-group">
        <label for="password">Password:</label>
        <input
          type="password"
          id="password"
          class="styled-input"
          v-model="user.password"
          required
        />
      </div>
      <button type="submit" class="submit-button">Sign in</button>
      <p class="signup-link">
        <router-link v-bind:to="{ name: 'register' }"
          >Need an account? Sign up.</router-link
        >
      </p>
    </form>
    <global-footer />
  </div>
</template>

<script>
import authService from "../services/AuthService";
import GlobalHeader from "../components/GlobalHeader.vue";
import GlobalFooter from "../components/GlobalFooter.vue";
export default {
  components: {
    GlobalHeader,
    GlobalFooter,
  },
  data() {
    return {
      user: {
        username: "",
        password: "",
        
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
.login-form {
  border: 3px solid #e9e7e7;
  display: flex;
  flex-direction: column;
  align-items: center;
  /* height: 80vh; */
  margin: auto;
  width: 320px;
  border-radius: 10px;
  margin-top: 60px;
  margin-bottom: 90px;
  background-color: #f9f9f9;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.login-header {
  margin-top: 20px;
  text-align: center;
  font-size: 2.5rem;
  color: #1d366f;
  letter-spacing: 2px;
  font-weight: bold;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
}

.submit-button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #1d366f;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-bottom: 20px;
}

.submit-button:hover {
  background-color: #77aae9;
}

.styled-input {
  border-radius: 5px;
  padding: 8px;
  border: 1px solid #ccc;
  width: calc(100% - 20px);
  margin-bottom: 15px;
}

.signup-link {
  text-align: center;
  margin-top: 20px;
}

.signup-link a {
  text-decoration: none;
  color: #1d366f;
  font-weight: bold;
  transition: color 0.3s ease;
}

.signup-link a:hover {
  color: #77aae9;
}

.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
  font-size: 1.2em;
  font-weight: bold;
  color: #333;
  letter-spacing: 1px;
  padding-left: 8px;
}
</style>
