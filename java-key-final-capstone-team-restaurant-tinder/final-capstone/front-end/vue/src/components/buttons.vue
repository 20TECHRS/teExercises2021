<template>
    <div class="buttonFooter">
        <img id="likeBtn" src="../../public/Green_CheckMark.png" v-on:click.prevent="pushToDatabase(); cycleRestaurant()"/>
        <img id="dislikeBtn" src="../../public/Red_CheckMark.png" v-on:click.prevent="cycleRestaurant" />

 <!--       <button id="likeBtn" type="submit" v-on:click.prevent="pushToDatabase(); cycleRestaurant()">✔</button>
        <button id="dislikeBtn" v-on:click.prevent="cycleRestaurant">✖</button> -->
    </div>
    
</template>

<script>
import applicationServices from "../services/ApplicationServices";
export default {
    name: 'buttons',

    methods: {
        cycleRestaurant() {
           // let currentRestaurant = this.$store.state.restaurants.data
           let updatedId = this.$store.state.restaurantId + 1;
           this.$store.commit("UPDATE_TO_NEXT_RESTAURANT", updatedId)
           // this.restaurantId++
       },

       pushToDatabase() {
           // Updating a liked restaurant to the data store
           let currentRestaurant = this.$store.state.restaurants[this.$store.state.restaurantId]
           this.$store.commit("LIKE_RESTAURANT", currentRestaurant)
           // Still need to call the service to push to database
           // Pass to the Database with our liked Restaurant
           this.$store.commit("SET_RESTAURANTDTO", currentRestaurant)
           applicationServices
           .addLikedRestaurant(this.$store.state.restaurantDTO)
           .then(response => {
               this.$store.commit("RESET_RESTAURANTDTO")}) 

       }

    }
    
}
</script>

<style scoped>

.buttonFooter {
    display: flex;
    justify-content: space-evenly;
}

#likeBtn {
    cursor: pointer;
    height: 60 px;
    width: 60px;
    border-radius: 50%;
}
#likeBtn:active {
    transform: translateY(4px);
}
#dislikeBtn {
    cursor: pointer;
    height: 60 px;
    width: 60px;
}
#dislikeBtn:active {
    transform: translateY(4px)
}

</style>