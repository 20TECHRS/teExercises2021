<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link> 
      &nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
       &nbsp;|&nbsp; 
       <router-link v-bind:to="{ name: 'user' }" v-if="$store.state.token != ''">Favorited Restaurants</router-link>
    </div>
    <router-view />
  </div>
</template>

<style scoped>
  #nav {
background-color: rgba(190, 186, 186, 0.911);  
border-bottom: outset;
border-width: medium;
border-color: rgba(92, 92, 92, 0.842);
padding: 6px;

}
</style>