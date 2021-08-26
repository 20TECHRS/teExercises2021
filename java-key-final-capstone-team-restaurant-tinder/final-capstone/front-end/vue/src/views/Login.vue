
<template>
  <div id="login" class="text-center main-grid">
    <form class="form-signin" @submit.prevent="login">
      <h1 id="katiesFont" class="title"> Mangiamo </h1>
      <h2 class="h3 mb-3 font-weight-normal">Please Sign In</h2>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>

      <div class= "user-pass">
        <label for="username" class="sr-only">Username </label>
        <br>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <br>
      <label for="password" class="sr-only">Password </label>
      <br>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      </div>
      <br>
      <button type="submit">Sign in</button>
      <br>
      <br>
      <router-link :to="{ name: 'register' }">Need an account?</router-link>

    </form>
  </div>
</template>
<script>
import applicationServices from '../services/ApplicationServices';
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);           
            applicationServices
            .getProfileById(this.$store.state.user.id)
            .then(res => {
              this.$store.commit("SET_PROFILE_DATA", res.data);
              applicationServices
              .getPreferenceById(this.$store.state.user.id)
              .then(res => {
                this.$store.commit("SET_PREFERENCE_DATA", res.data); // need a service to get preference and profile
                this.$router.push("/");
              })
            }) 
          }
        })
        .catch(error => {
          const response = error.response;
          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>


<style scoped>

#katiesFont {
  font-family: 'Pacifico', cursive;
}

.text-center {
  text-align: center;
  font-family: Monospace, Cursive, Sans-serif;
 
}




#login {
  
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows: 100px 2fr 100px;
  grid-template-areas:
  ". . ."
  ". form-signin ."
  ". . ."

  
  

}

div.app {
  height: 100%;
}





.form-signin {
  grid-area: form-signin;
  background-color: rgba(190, 186, 186, 0.911);
  height: auto;
  margin-top: 5%;
  padding-bottom: 11%;
  border-style: outset;
  border-width: medium;
  border-color: rgba(92, 92, 92, 0.842);
  border-radius: 10px;
}

h1 {
  color: rgb(204, 10, 10);
}

input {
  margin-bottom: 5%;
  border-style: inset;
  border-width: medium;
  border-color: rgba(92, 92, 92, 0.712);
}

</style>