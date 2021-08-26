<template>
<div class="restaurants text-center">
<div class="restaurantCard">
    <h1 id="katiesFont"> Favorite Restaurants </h1>
    <div class="imageGrid"> 
        <img  v-bind:src="this.$store.state.favoritedRestaurant.imageLink" alt="Photo of the Restaurant" class="restaurantImg">
    </div>
        <div class="restaurantDetails">
                <h3>Restaurant Name:  <span>{{this.$store.state.favoritedRestaurant.restaurantName}} </span> </h3>
                <h5>Address: <span>{{this.$store.state.favoritedRestaurant.address}}</span></h5>
                <h4>Cuisine Type & Dietary Restrictions: </h4>
                <div class="typesGrid" >
                    <div class="types">
                        <h5 class="atype" v-for="cuisineObject in this.$store.state.favoritedRestaurant.cuisineTypes"
                            v-bind:key="cuisineObject.id"><img src="../../public/Food_icon.png" id="foodIcon" v-if="cuisineObject.key != 'null'"> {{cuisineObject.name != "null" ? cuisineObject.name : ""}} </h5>
                    </div>
                </div>    
                <p>Phone: {{this.$store.state.favoritedRestaurant.restaurantPhone}}</p>
                <p v-if="canDisplayWebsite()">Visit: 
                    <a v-bind:href="this.$store.state.favoritedRestaurant.restaurantWebsite" target="_blank">
                        {{this.$store.state.favoritedRestaurant.restaurantName}}'s website
                    </a>
                </p>
                <p v-else>Website Not Listed </p>
                <p>Price: {{this.$store.state.favoritedRestaurant.priceRange}} 
    
                </p>
            </div>
        <div id="buttonFooter">
            <button id="nextButton" v-on:click.prevent="cycleRestaurants">Next</button>
        </div>
    </div>
</div>

</template>

<script>
import axios from 'axios';
import ApplicationServices from '../services/ApplicationServices';


export default {
    name: 'liked-restaurant',
    components: {
    },
    data() {
        return {
        }
    },
    created() {
        this.$store.commit("CLEAR_RESTAURANT_ID")
        this.retrieveRestaurants() 
    },
    methods : {
        retrieveRestaurants() {
            ApplicationServices
            .getLikedRestaurants(this.$store.state.user.id)
            .then(response => {
                this.$store.commit("SET_LIKED_RESTAURANTS", response.data);
                this.$store.commit("SET_FAVORITED_RESTAURANT", this.$store.state.restaurantId);
            })
            
        },
        canDisplayCuisine() {
           let cuisine = this.$store.state.favoritedRestaurant.cuisine
           if(cuisine != null) {
               if(cuisine.size >= 0) {
                   return true;
               }   
           }
           return false;
       },
       canDisplayWebsite() {
           let website = this.$store.state.favoritedRestaurant.restaurantWebsite
           if(website != null && website != '') {
               return true;
           }
       },
       cycleRestaurants() {
           let updatedId = this.$store.state.restaurantId + 1;
           this.$store.commit("UPDATE_TO_NEXT_LIKED_RESTAURANT", updatedId)
       }
    }
}

</script>

<style scoped> 
.restaurants {
        display: grid;
        grid-template-columns: 1fr 3fr 1fr;
        height: 100%;
        grid-template-areas:
            ". restaurantCard ."
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
    height: 100%;
    grid-template-areas:
    "h1 h1"
    "restaurantDetails imageGrid"
    "nextButton nextButton"

}

#buttonFooter {
    grid-area: nextButton;
}

#nextButton {
    width: 200px;
}

#restaurantDetailsComponent {
    grid-area: restaurant-details;
}
.restaurantDetails {
    grid-area: restaurantDetails;
    padding: 3%;
    display: inline-block;
    max-width: auto;    
}

#restaurantDetailsComponent {
    grid-area: restaurant-details;
}



.restaurantDetails {
    grid-area: restaurantDetails;
    padding: 3%;
    display: inline-block;
    max-width: auto;  
}



h1{
    grid-area: h1;
    text-align: center;
    color: rgb(204, 10, 10);
    margin: 0%;
    border: 5px ridge red;
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