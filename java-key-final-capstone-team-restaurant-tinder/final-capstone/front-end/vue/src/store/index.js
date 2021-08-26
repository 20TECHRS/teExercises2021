import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));


if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    editingProfile: false,
    displayMessage: false,
    locationArray: [
      "41.49932",
      "-81.694361"
    ],
    token: currentToken || '',
    user: currentUser || {},
    restaurants: [],
    currentRestaurant: {},
    restaurantId: 0,
    likedRestaurant: {},
    profile:{},     // Hold the current users profile data
    preference:{},   // Hold current users preference data
    cuisines: [
      {key: '10660',
      name: 'Thai '},
      {key: '9908',
      name: 'American'}, 
      {key:'10641',
      name: 'Pizza '},
      {key: '10664',
      name : 'Greek '},
      {key: '4617',
      name: 'Italian '},
      {key: '10666 ',
      name: 'Deli'},
      {key: '10687',
      name: 'Middle Eastern'},
      {key: '5473',
      name: 'Japanese '},
      {key: '10653',
      name: 'Sushi '},
      {key: '10659', 
      name: 'Asian '},
      {key: '10649', 
      name: 'Mediterranean '},
      {key: '10640',
      name: 'Bar '},
      {key: '10345',
       name: 'Steakhouse '},
      {key: '10670', 
      name: 'Pub '},
      {key: '5379', 
      name: 'Chinese '},
      {key: '10642', 
      name: 'Café '},
      {key: '10679', 
      name: 'Healthy '},
      {key: '10634', 
      name: 'Southwestern '},
      {key: '5110', 
      name: 'Mexican '},
      {key: '10646', 
      name: 'Fast Food'},
      {key: '10665', 
      name: 'Vegetarian Friendly'},
      {key: '10697', 
      name: 'Vegan Options'},
      {key: '10992',
      name: 'Gluten Free Options'},
    ],
    restaurantDTO: {
      restaurantToAdd: {
       restaurantId: Number,
       locationId: Number,
       restaurantName: '',
       description: '',
       imageLink: '',
       restaurantPhone: '',
       restaurantWebsite: '',
       priceRange: '',
       cuisineTypes:[],
       address: '',
       lat: '',
       lon: '',
     },
     currentProfile: {
         userId: '',   
         firstName: '',
         lastName: '', 
         dateOfBirth: '',  
         email: '', 
         zipCode: '',  
     }
 },
    likedRestaurants: [],
    favoritedRestaurant: {}
  },

  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_RESTAURANTS(state, data) { 
      let filterData = []
            for(let i=0; i<data.data.length; i++) {
              let restaurant = data.data[i]
                if("name" in restaurant && 
                  "address" in restaurant && 
                  "cuisine" in restaurant && 
                  "website" in restaurant && 
                  "phone" in restaurant &&
                  "photo" in restaurant) {
                    if("images" in restaurant.photo) {
                    filterData.push(data.data[i])
                    }
                }
              }
      state.restaurants = filterData;
      state.currentRestaurant = filterData[0];
    },
    
    SET_PREFERENCE_DATA(state, preference) { 
      state.preference = preference;
    },
    SET_PROFILE_DATA(state, profile) {
      state.profile = profile;
      state.restaurantDTO.currentProfile = profile;
    },

    UPDATE_TO_NEXT_RESTAURANT(state, nextRestaurantId) {
      if(nextRestaurantId > state.restaurants.length) {
        this.displayMessage = true;
      } else {
        state.currentRestaurant = this.state.restaurants[nextRestaurantId]
        state.restaurantId = nextRestaurantId
      }
    },
    
    LIKE_RESTAURANT(state, currentRestaurant) {
      state.likedRestaurant = currentRestaurant
    },

    SET_RESTAURANTDTO(state, restaurant) {
      state.restaurantDTO.restaurantToAdd.restaurantName = restaurant.name
      state.restaurantDTO.restaurantToAdd.locationId = restaurant.location_id
      state.restaurantDTO.restaurantToAdd.description = restaurant.description
      state.restaurantDTO.restaurantToAdd.imageLink = restaurant.photo.images.large.url
      state.restaurantDTO.restaurantToAdd.restaurantPhone = restaurant.phone
      state.restaurantDTO.restaurantToAdd.restaurantWebsite = restaurant.website
      state.restaurantDTO.restaurantToAdd.priceRange = restaurant.price_level

      for (let i = 0; i < restaurant.cuisine.length; i++) {
        state.restaurantDTO.restaurantToAdd.cuisineTypes.push(restaurant.cuisine[i])
      }

      state.restaurantDTO.restaurantToAdd.address = restaurant.address
      state.restaurantDTO.restaurantToAdd.lat = restaurant.latitude
      state.restaurantDTO.restaurantToAdd.lon = restaurant.longitude
    },

    RESET_RESTAURANTDTO(state) {
      state.restaurantDTO.restaurantToAdd.restaurantName = '',
      state.restaurantDTO.restaurantToAdd.locationId = Number,
      state.restaurantDTO.restaurantToAdd.description = '',
      state.restaurantDTO.restaurantToAdd.imageLink = '',
      state.restaurantDTO.restaurantToAdd.restaurantPhone = '',
      state.restaurantDTO.restaurantToAdd.restaurantWebsite = '',
      state.restaurantDTO.restaurantToAdd.priceRange = '',
      state.restaurantDTO.restaurantToAdd.cuisineTypes = [],
      state.restaurantDTO.restaurantToAdd.address = '',
      state.restaurantDTO.restaurantToAdd.lat = '',
      state.restaurantDTO.restaurantToAdd.lon = ''
    },

    SET_LAT_LONG(state, array) {
      state.locationArray = array
    },

    CLEAR_RESTAURANT_DATA(state) {
      state.restaurants = ''
    },

    CLEAR_RESTAURANT_ID(state) {
      state.restaurantId = 0;
    },

    CLEAR_DISPLAY_MESSAGE(state) {
      state.displayMessage = false;
    },

    TRUE_EDITING_PROFILE(state) {
      state.editingProfile = true;
    },

    FALSE_EDITING_PROFILE(state) {
      state.editingProfile = false;
    },
    SET_LIKED_RESTAURANTS(state, data) {
      state.likedRestaurants = data
    },
    SET_FAVORITED_RESTAURANT(state, restaurantId) {
      state.favoritedRestaurant = this.state.restaurants[restaurantId]
    },

    UPDATE_TO_NEXT_LIKED_RESTAURANT(state, nextRestaurantId) {
      if(nextRestaurantId > state.likedRestaurants.length) {
        this.displayMessage = true;
      } else {
        state.favoritedRestaurant = this.state.likedRestaurants[nextRestaurantId]
        state.restaurantId = nextRestaurantId
      }
  }
}
})


