<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 id="katiesFont" class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only">Username</label>
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
      <label for="password" class="sr-only">Password</label>
      <br>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <br>
      <label for="confirm password">Confirm Password</label >
      <br>
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <br>
      <br>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Continue to Questionnaire
      </button>
      <br>
      <br>
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$store.commit("SET_USER", response.data);
              this.$router.push({
                path: '/questionnaire',
                query: {id:response.data.id, registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
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


h1 {
  color: rgb(204, 10, 10);
}


#register {

  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows: 100px 2fr 100px;
  grid-template-areas: 
  ". . ."
  " . form-register ."
  ". . ."

}

.form-register {
  grid-area: form-register;
  background-color: rgba(190, 186, 186, 0.911);
  height: 350px;
  padding: 5%;
  border-style: outset;
  border-width: medium;
  border-color: rgba(92, 92, 92, 0.842);
  border-radius: 10px;
}

input {
  margin-bottom: 5%;
  border-style: inset;
  border-width: medium;
}


</style>