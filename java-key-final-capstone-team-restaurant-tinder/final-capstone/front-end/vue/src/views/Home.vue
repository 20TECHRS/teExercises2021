<template>
  <div class="home ">
    <restaurants/>
  </div>
</template>

<script>
import restaurants from '../components/restaurants.vue';
import applicationServices from '../services/ApplicationServices';
export default {
  components: { restaurants },
  name: "home",
  created() {

    /*ApplicationServices
            .getProfileById(this.$store.state.user.id)
            .then(res => {
              this.$store.commit("SET_PROFILE_DATA", res.data);
              ApplicationServices
              .getPreferenceById(this.$store.state.user.id)
              .then(res => {
                this.$store.commit("SET_PREFERENCE_DATA", res.data); // need a service to get preference and profile
              })
            }) */
  }
};

</script>

<style scoped>

</style>
