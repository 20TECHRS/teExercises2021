<template>
    <div class="restaurants text-center">
        <div class="rightSideArea"> 
        <h1 class="chooseCity">Choose City</h1>
        <change-location class="changeLocation" />
        </div>
        <div class="leftSideArea" v-if="this.$store.state.editingProfile === false" >
            <h1 class="preferences">Preferences</h1>
            <profile class="profile" />
        </div>
        <div class="leftSideArea" v-if="this.$store.state.editingProfile === true" >
            <h1 class="preferences">Preferences</h1>
            <update-profile class="profile" />
        </div>
        <div class="restaurantCard">
            <h1 id="katiesFont"> Mangiamo </h1>
            <div class="imageGrid">
            <img  v-if="this.$store.state.currentRestaurant.photo.images != null" v-bind:src="this.$store.state.currentRestaurant.photo.images.large.url" alt="Photo of the Restaurant" class="restaurantImg">
            </div>
            <div class="restaurantDetails">
                <h3>Restaurant Name:  <span>{{this.$store.state.currentRestaurant.name}} </span> </h3>
                <h5>Address: <span>{{this.$store.state.currentRestaurant.address}}</span></h5>
                <h4>Cuisine Type & Dietary Restrictions: </h4>
                <div class="typesGrid" >
                    <div class="types">
                        <h5 class="atype" v-for="cuisineObject in this.$store.state.currentRestaurant.cuisine"
                            v-bind:key="cuisineObject.id"><img src="../../public/Food_icon.png" id="foodIcon"> {{cuisineObject.name}} </h5>
                    </div>
                </div>    
                <p>Phone: {{this.$store.state.currentRestaurant.phone}}</p>
                <p v-if="canDisplayWebsite()">Visit: 
                    <a v-bind:href="this.$store.state.currentRestaurant.website" target="_blank">
                        {{this.$store.state.currentRestaurant.name}}'s website
                    </a>
                </p>
                <p v-else>Website Not Listed </p>
                <p v-if="this.$store.state.currentRestaurant.price != null">Price: {{this.$store.state.currentRestaurant.price_level}} 
                    ({{this.$store.state.currentRestaurant.price}}) 
                </p>
            </div>

           <buttons id="buttonsComponent"/>

           <restaurant-details id="restaurantDetailsComponent"/>   
        </div>
    </div>    


</template>


<script>
import applicationServices from '../services/ApplicationServices'
import axios from "axios";
import buttons from '../components/buttons.vue'
import restaurantDetails from '../components/restaurantDetails.vue'
import RestaurantDetails from '../components/restaurantDetails.vue';
import ChangeLocation from './changeLocation.vue';
import Profile from './profile.vue';
import UpdateProfile from './updateProfile.vue';

export default {
   name: 'restaurants',
   components: {
       buttons,
      RestaurantDetails,
      ChangeLocation,
      Profile,
      UpdateProfile
   },

   data() {
       return {
           //restaurantId: 0,     
           filteredRestaurants: [], // Holds our Restaurants filtered by Preferences
           trueFalse: false
       }
   }, 
   created() {
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

        axios.request(options)
        .then( (response) => {
            this.$store.commit("SET_RESTAURANTS", response.data)

        });
   },
   computed: { 
       
       
    }, 
   methods: {

       canDisplayCuisine() {
           let cuisine = this.$store.state.currentRestaurant.cuisine
           if(cuisine != null) {
               if(cuisine.size >= 0) {
                   return true;
               }   
           }
           return false;
       },

       canDisplayWebsite() {
           let website = this.$store.state.currentRestaurant.website
           if(website != null && website != '') {
               return true;
           }
       },

        
   } // end of Methods
} // end of export default

</script>


<style scoped>

#award {
    height: 100px;
    width: 100px;
}

button {
    
    border-radius: 12px;
    background-color: white;
    color: black;
    transition-duration: 0.6s;
    cursor: pointer
    
}

button:hover {
  background-color: #e7e7e7;
  color: black;
}

.restaurants {
        display: grid;
        grid-template-columns: 1fr 3fr 1fr;
        height: 100%;
        grid-template-areas:
            "leftSideArea restaurantCard rightSideArea"
    }

    .rightSideArea {
        grid-area: rightSideArea;
        margin-top: 9.52%;
        display: grid;
        grid-template-rows: .185fr .25fr 1fr 1fr 1fr 1fr;
        grid-template-areas: 
        "chooseCity"
        "changeLocation"
        "."
        "."
        "."
        ".";
    }

     .leftSideArea {
        grid-area: leftSideArea;
        margin-top: 9.52%;
        display: grid;
        grid-template-rows: .21fr 1fr 1fr 1fr 1fr 1fr;
        grid-template-areas: 
        "preferences"
        "profile"
        "."
        "."
        "."
        ".";
    }

    .preferences {
        grid-area: preferences;
        font-family: 'Pacifico', cursive;
        background-color: rgba(190, 186, 186, 0.911);
    }

    .profile {
      grid-area: profile;
        padding: 7%;
        background-color: rgba(190, 186, 186, 0.911);  
    }

    .chooseCity {
        grid-area: chooseCity;
        font-family: 'Pacifico', cursive;
        background-color: rgba(190, 186, 186, 0.911);
    }

    .changeLocation {
        grid-area: changeLocation;
        padding:10%;
        background-color: rgba(190, 186, 186, 0.911);

    }

.restaurantCard {
    grid-area: restaurantCard;
    background-color: rgba(190, 186, 186, 0.911);
    border-color: rgba(92, 92, 92, 0.842);
    border-style: outset;
    border-width: medium;
    border-radius: 10px;
    margin-top: 3%;

    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-rows: 75px .3fr .005fr .005fr;
    /*height: 100vw; implement at later time- @mediaqueries???*/ 
    height: 100%;
    margin-left: 1%;
    margin-right: 1%;
    grid-template-areas:
    "h1 h1"
    "restaurantDetails imageGrid"
    "buttons buttons"
    "restaurant-details restaurant-details"    
}

#buttonsComponent {
    grid-area: buttons;
}

#restaurantDetailsComponent {
    grid-area: restaurant-details;
}


.descriptionGrid {
    grid-area: descriptionGrid;
    
}

.restaurantDetails {
    grid-area: restaurantDetails;
    padding: 3%;
    display: inline-block;
    max-width: auto;  
}

.buttonFooter {
    grid-area: btn;
}

h1{
    grid-area: h1;
    text-align: center;
    color: rgb(204, 10, 10);
    margin: 0%;
    border: 5px ridge rgb(204, 10, 10);
    border-radius: 10px;
}
h4{
    margin-top: 2%;
    margin-bottom: 0%;
}
.imageGrid {
    grid-area: imageGrid;
    height: auto;
}
.restaurantImg {
    object-fit: cover;
    width: 350px;
    height: 350px;
    margin: 10% 0% 10% 0%;
    
}
.text-center {
    text-align: center;
    font-family: Monospace, Cursive, Sans-serif;
    }
#katiesFont {
  font-family: 'Pacifico', cursive;
}
#foodIcon {
    height: 15px;
    width: 15px;
}
.typesGrid {
    display: grid;
    grid-template-columns: 1fr 3fr 1fr;
    height: auto;
    grid-template-areas:
    ". types .";
}
.types {
    grid-area: types;
    text-align: left;
}
.atype {
    margin: 2%;
}











</style>