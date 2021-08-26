<template>
<div>
    <label for="foodtype1">Preferred Types of Cuisine: </label>
      <br>
      <select name="foodtype1" class="foodtype" v-model="preferences.cuisineStyle1" >
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
      <!-- <p> Price: </p>
      <input type="radio" name="$" value="$" v-model="preferences.pricePoint">
      <label for="$"> $ </label>
      <input type="radio" name="$" value="$$" v-model="preferences.pricePoint">
      <label for="$"> $$ </label>
      <input type="radio" name="$" value="$$$" v-model="preferences.pricePoint">
      <label for="$"> $$$ </label>
      <input type="radio" name="$" value="$$$$" v-model="preferences.pricePoint">
      <label for="$"> $$$$ </label><br> -->
      <br>
      <br>
      <button class="submitPrefBtn" type="submit"
      v-on:click.prevent="checkCheckBoxes(); submitUpdatedPreference()">
        Save Changes
      </button>
</div>



</template>
<script>
import ApplicationServices from '../services/ApplicationServices'
import axios from "axios";


export default {
  name: 'update-profile',
  data() {
    return {
      dietaryRestrictionsArray: [],
      preferences: {
        userId: '', /* figure how to current UserID passed in*/
        cuisineStyle1: '',
        cuisineStyle2: '',
        cuisineStyle3: '',
        pricePoint: '',
        dietaryRestrictions: ''
      }        
    }
  }, // End of Data

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

     submitUpdatedPreference() {
        // Set user Id for the new preferences
        this.preferences.userId = this.$store.state.profile.userId
        this.preferences.pricePoint = this.$store.state.preference.pricePoint
        this.$store.commit("CLEAR_RESTAURANT_DATA")
        this.$store.commit("CLEAR_RESTAURANT_ID")
        // Call service to update new preferences in DB
        ApplicationServices
        .updatePreferences(this.preferences)
        .then(response => {
          // Once new preferences is set in DB, call service to get that new preference at set it in the datastore
          ApplicationServices
          .getPreferenceById(this.$store.state.profile.userId)
          .then(response => {
            // Set new preferences to datastore
            this.$store.commit("SET_PREFERENCE_DATA", this.preferences)
            //Set up to call API
            let preferences = this.$store.state.preference
            let dietaryRestrictions = this.$store.state.preference.dietaryRestrictions
            let usersCuisines = preferences.cuisineStyle1 + ", " + preferences.cuisineStyle2 + ", " + preferences.cuisineStyle3
            const options = {
              method: 'GET',
              url: 'https://travel-advisor.p.rapidapi.com/restaurants/list-by-latlng',
              params: {
                latitude: this.$store.state.locationArray[0],
                longitude: this.$store.state.locationArray[1],
                limit: '100',
                currency: 'USD',
                combined_food: usersCuisines,
                distance: '20',
                dietary_restrictions: dietaryRestrictions,
                lunit: 'km',
                lang: 'en_US'
              },
              headers: {
                'x-rapidapi-key': '14a46059f3msh988e7d991f2e1b8p1364a6jsn76d4c5b639c7',
                'x-rapidapi-host': 'travel-advisor.p.rapidapi.com'
              }
            };
            // Call the API
            axios.request(options)
            .then( (response) => {
              // Set new restaurants based on new preferences
              this.$store.commit("SET_RESTAURANTS", response.data)
            });
            // Exit updateProfile component
            this.$store.commit("FALSE_EDITING_PROFILE")
          })
        })
            
         
    } // End of Submit Method 

  }// End of Method
} // End of Export

</script>



<style scoped>

.submitPrefBtn:hover{
  box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
}

</style>

