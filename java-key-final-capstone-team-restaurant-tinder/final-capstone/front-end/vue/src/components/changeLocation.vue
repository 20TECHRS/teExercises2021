<template>
    <div id="cityContainer">
    <select name="cityDropdownMenu" class="dropdown" v-model="latLongString">
        <option value="" selected disabled hidden>Select a City</option>
        <option value="33.4484, -112.0740">Phoenix, AZ</option>
        <option value="34.0522, -118.2437">Los Angeles, CA</option>
        <option value="37.7736, -122.4126">San Francisco, CA</option>
        <option value="39.7421, -104.9724">Denver, CO</option>
        <option value="30.3591, -81.5511">Jacksonville, FL</option>
        <option value="39.7779, -86.1574">Indianapolis, IN</option>
        <option value="41.8781, -87.6298">Chicago, IL</option>
        <option value="42.3624, -71.0618">Boston, MA</option>
        <option value="42.6866, -73.7432">Albany, NY</option>
        <option value="42.8839, -78.8094">Buffalo, NY</option>
        <option value="40.7128, -74.0060">New York City, NY</option>
        <option value="35.2907, -80.7440">Charlotte, NC</option>
        <option value="41.0814, -81.5190">Akron, OH</option>
        <option value="41.4993, -81.6944">Cleveland, OH</option>
        <option value="39.9887, -83.0104">Columbus, OH</option>
        <option value="39.9526, -75.1652">Philadelphia, PA</option>
        <option value="32.7767, -96.7970">Dallas, TX</option>
        <option value="29.7604, -95.3698">Houston, TX</option>
        <option value="47.6036, -122.3358">Seattle, WA</option>
        <option value="38.9113, -77.0407">Washington D.C.</option>
      </select>
      <br>
      <button class = "buttonWrapper" v-on:click.prevent="splitString">Confirm</button>
    </div>
</template>



<script>
import axios from "axios";

export default {

    name: 'change-location',
    data() { 

        return {

        latLongString:'',
        locationArray: []
        }
    },
    methods: {
        splitString() {
            this.locationArray = this.latLongString.split(",")
            // method to split latLongString at the comma
            // computed method to call splitString
            this.$store.commit("SET_LAT_LONG", this.locationArray)
            this.$store.commit("CLEAR_RESTAURANT_DATA")
            this.$store.commit("CLEAR_RESTAURANT_ID")
            
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
        }
    }
}
</script>


<style scoped>

.buttonWrapper:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
}

.buttonWrapper {
    margin: 15px;
}

#cityContainer{
    border-style: outset;
    border-width: medium;
    border-radius: 10px;
    background-color: rgba(190, 186, 186, 0.911);
    border-color: rgba(92, 92, 92, 0.842);
    
}

</style>