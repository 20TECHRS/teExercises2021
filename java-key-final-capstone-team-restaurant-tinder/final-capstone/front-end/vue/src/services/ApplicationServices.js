/**************************************************************************************
* This file is provided for you to use for any Application services you may create
*
*  If you would prefer a file with a different name for your services, 
*     simply create one 
***************************************************************************************/
import axios from 'axios';



export default {

addProfile(profile) {
    return axios.post('/user', profile)
},

updatePreferences(preferences) {
    return axios.put(`/user/preference`, preferences)
},

addPreferences(preferences) {
    return axios.post('/user/preference', preferences)
},

getUserByUserName(username) {
    return axios.get(`/user/getuser/${username}`)
},

getProfileById(id) {
    return axios.get(`/user/${id}`)
},

getPreferenceById(id) {
    return axios.get(`/user/${id}/preference`)
},

addLikedRestaurant(restaurantDTO) {
    return axios.post(`/restaurants`, restaurantDTO)
},

getLikedRestaurants(id) {
    return axios.get(`/user/${id}/restaurants`)

}
}