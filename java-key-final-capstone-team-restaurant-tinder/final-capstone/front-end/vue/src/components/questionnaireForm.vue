<template>
    <div class="questionnaire text-center"> 
    <form class="form-questionnaire" @submit.prevent="checkCheckBoxes(); submitQuestionnaire()">
    <h1 id="katiesFont">Questionnaire</h1>
    <label for="firstName">First Name </label>
      <input v-model="profile.firstName"
        type="text"
        id="firstName"
        class="form-control"
        placeholder="First Name"
        required
        autofocus
      />
      <br>
      <label for="lastName">Last Name </label>
      <input v-model="profile.lastName"
        type="text"
        id="lastName"
        class="form-control"
        placeholder="Last Name"
        required
      />
      <br>
      <label for="DOB">Date of Birth </label>
      <input type="date" id="DOB" name="DOB" v-model="profile.dateOfBirth" required>
      <br>
      <label for="email">Email </label>
      <input id="email" name="email" type="email" placeholder="email@address.com" v-model="profile.email" required="required">
      <br>
      <label for="zipcode">Zip Code </label>
      <input id="zipcode" type="text" name="zipcode" v-model="profile.zipCode">
      <br>
      <label for="foodtype1">Preferred Types of Cuisine: </label>
      <br>
      <select name="foodtype1" class="foodtype" v-model="preferences.cuisineStyle1">
        <option value="none" selected disabled hidden>
          Select an Option
        </option>  
        <option value="9908">American</option>
        <option value="10664">Greek</option>
        <option value="5379">Chinese</option>
        <option value="4617">Italian</option>
        <option value="10687">Middle Eastern</option>
        <option value="5110">Mexican</option>
        <option value="10641">Pizza</option>
        <option value="10642">Café</option>
        <option value="10345">Steak</option>
        <option value="10653">Sushi</option>
        <option value="10660">Thai</option>
      </select>
      <select name="foodtype2" class="foodtype" v-model="preferences.cuisineStyle2">
        <option value="none" selected disabled hidden>
          Select an Option
        </option>  
        <option value="9908">American</option>
        <option value="10664">Greek</option>
        <option value="5379">Chinese</option>
        <option value="4617">Italian</option>
        <option value="10687">Middle Eastern</option>
        <option value="5110">Mexican</option>
        <option value="10641">Pizza</option>
        <option value="10642">Café</option>
        <option value="10345">Steak</option>
        <option value="10653">Sushi</option>
        <option value="10660">Thai</option>
      </select>
      <select name="foodtype3" class="foodtype" v-model="preferences.cuisineStyle3">
        <option value="none" selected disabled hidden>
          Select an Option
        </option>  
        <option value="9908">American</option>
        <option value="10664">Greek</option>
        <option value="5379">Chinese</option>
        <option value="4617">Italian</option>
        <option value="10687">Middle Eastern</option>
        <option value="5110">Mexican</option>
        <option value="10641">Pizza</option>
        <option value="10642">Café</option>
        <option value="10345">Steak</option>
        <option value="10653">Sushi</option>
        <option value="10660">Thai</option>
      </select>
      <br>
      <br>
      <p> Dietary Restrictions: </p>

      <input type="checkbox" name="dietary1" value="10992" v-model="dietaryRestrictionsArray">
      <label class="dietarybox" for="dietary1"> Gluten Free</label>
      <input type="checkbox" name="dietary2" value="10697" v-model="dietaryRestrictionsArray">
      <label class="dietarybox" for="dietary2"> Vegan</label>
      <input type="checkbox" name="dietary3" value="10665" v-model="dietaryRestrictionsArray">
      <label class="dietarybox" for="dietary3"> Vegetarian</label>

      <br>
      <br>
      <p> Price: </p>
      <input type="radio" name="$" value="$" v-model="preferences.pricePoint">
      <label for="$"> $ </label>
      <input type="radio" name="$" value="$$" v-model="preferences.pricePoint">
      <label for="$"> $$ </label>
      <input type="radio" name="$" value="$$$" v-model="preferences.pricePoint">
      <label for="$"> $$$ </label>
      <input type="radio" name="$" value="$$$$" v-model="preferences.pricePoint">
      <label for="$"> $$$$ </label><br>
      <br>
      <br>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
      <br>
      <br>
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
    </form>
    </div>    
</template>



<script>
import applicationServices from '../services/ApplicationServices';

export default {
    name: 'questionnaire-form',
    data() {
        return {
            dietaryRestrictionsArray: [],
            vegan: '',
            glutenFree: '',
            vegitarian: '',

            profile: {
                userId: '', /* figure how to current UserID passed in*/
                firstName: '',
                lastName: '',
                dateOfBirth: '',
                email: '',
                zipCode: ''
            },
            preferences: {
                 userId: '', /* figure how to current UserID passed in*/
                 cuisineStyle1: '',
                 cuisineStyle2: '',
                 cuisineStyle3: '',
                 pricePoint: '',
                 dietaryRestrictions: '',
                 // vegan: '',
                 // vegetarian: '',
                 // glutenFree: ''
            },
            
        }
    }, // END OF DATA
    created() {
    },
    methods: {
        checkCheckBoxes(event) {
        let restrictions = this.dietaryRestrictionsArray
        
        if(restrictions === null) {
           restrictions.push(0, 0, 0)
        }
        if(restrictions.length === 1) {
          restrictions.push(0, 0)
        }
        if(restrictions.length === 2) {
          restrictions.push(0)
        }
        this.preferences.dietaryRestrictions = restrictions.toString();
      },

        submitQuestionnaire() {
        
            this.profile.userId = this.$route.query.id;
            applicationServices
            .addProfile(this.profile)
            .then(response => {
                this.preferences.userId = this.$route.query.id;
                applicationServices
                .addPreferences(this.preferences)
                .then(response => {
                this.$router.push({
                path: '/login' 
                })
                })
            });
        } // END OF SUBMITQUESTIONNAIRE

    },

    computed:{
      
    }
}
</script>

<style scoped>

#katiesFont {
  font-family: 'Pacifico', cursive;
}
    h1{
        color: rgb(204, 10, 10);
    }

    .questionnaire {
        display: grid;
        grid-template-columns: 1fr 3fr 1fr;
        grid-template-rows: 100px 2fr 100px;
        height: 100%;
        grid-template-areas:
            ". . ."
            ". form-questionnaire ."
            ". . ."
    }

    .form-questionnaire {
        grid-area: form-questionnaire;
        background-color: rgba(190, 186, 186, 0.911);
        height: 550px;
        padding: 5%;
        border-style: outset;
        border-width: medium;
        border-color: rgba(92, 92, 92, 0.842);
        border-radius: 10px;
    }

    .text-center {
        text-align: center;
        font-family: Monospace, Cursive, Sans-serif;
    }

    .foodtype {
        margin: 2%;
        border-style: inset;
        border-width: medium;
        border-color: rgba(92, 92, 92, 0.712);
    }

    .dietarybox {
        margin: 2%;
    }

    p {
        margin: 0px;
    }

    input {
        border-style: inset;
        border-width: medium;
        border-color: rgba(92, 92, 92, 0.712);
    }
</style>