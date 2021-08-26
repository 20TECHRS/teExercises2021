<template>
  <div>
    <!--<updated-profile/>-->
    <liked-restaurants/>
  </div>
</template>

<script>
import LikedRestaurants from '../components/LikedRestaurants.vue'

//import updateProfile from "../components/updateProfile.vue";//
import profile from "../components/profile.vue"

export default {
      name: 'user',
      components : {
          profile,
          //updateProfile//
            LikedRestaurants
         
      }

} // end of export
</script>

<style scoped>
    
    
</style>