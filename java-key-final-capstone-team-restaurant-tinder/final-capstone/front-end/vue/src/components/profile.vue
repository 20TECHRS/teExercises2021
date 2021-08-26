<template>
<div>
    <h4>Cuisine Filters: </h4>
    <p>{{CurrentUserCuisine1}}</p>
    <p>{{CurrentUserCuisine2}}</p>
    <p>{{CurrentUserCuisine3}}</p>
    <!-- <h4>Price Point: </h4>
    <p>{{this.$store.state.preference.pricePoint}}</p> -->
    <h4>Dietary Restrictions: </h4>
    <p>{{dietaryRestrictions}}</p>
 
    <br>
    
    <button class= "editPrefBtn" v-on:click.prevent="editProfile">Edit Profile</button>
 
    
  


</div>

</template>

<script>

export default {
  components: {  },
  name: 'profile',
  data () {
    return {
      CurrentUserCuisine1: '',
      CurrentUserCuisine2: '',
      CurrentUserCuisine3: '',
      restriction1: '',
      restriction2: '',
      restriction3: '',
      dietaryRestrictions: '',
      
    }
  }, // end of data
  created() {
    let id1 = this.$store.state.preference.cuisineStyle1
    let id2 = this.$store.state.preference.cuisineStyle2
    let id3 = this.$store.state.preference.cuisineStyle3

    this.CurrentUserCuisine1 = this.$store.state.cuisines.find(cuisine => cuisine.key === id1).name
    this.CurrentUserCuisine2 = this.$store.state.cuisines.find(cuisine => cuisine.key === id2).name
    this.CurrentUserCuisine3 = this.$store.state.cuisines.find(cuisine => cuisine.key === id3).name

    if(this.$store.state.preference.dietaryRestrictions != '') {
      let restrictions = this.$store.state.preference.dietaryRestrictions.split(",")
   
      if (restrictions != 0) {
        this.restriction1 = this.$store.state.cuisines.find(cuisine => cuisine.key === restrictions[0]).name
        this.dietaryRestrictions = this.restriction1
      }
      if (restrictions[1] != 0) {
        this.restriction2 = this.$store.state.cuisines.find(cuisine => cuisine.key === restrictions[1]).name
        this.dietaryRestrictions = this.restriction1 + " and " + this.restriction2
      }

      if (restrictions[2] != 0) {
        this.restriction3 = this.$store.state.cuisines.find(cuisine => cuisine.key === restrictions[2]).name
        this.dietaryRestrictions = this.restriction1 + ", " + this.restriction2 + ", and " + this.restriction3
      }
    }
  }, // End of Created 

  methods: {
    editProfile() {
      this.$store.commit("TRUE_EDITING_PROFILE")
    }
  }

}
</script>

<style scoped>

.editPrefBtn:hover{
  box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
}
</style>

