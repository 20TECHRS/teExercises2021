/**************************************************************************************
* This file contains services used by the login front-end starter code
*
* DO NOT MODIFY THIS CODE! YOU MAY RENDER IT UNUSABLE!
*
* Consider putting any services required by your application in a different file.
***************************************************************************************/
import axios from 'axios';

/**************************************************************************************
* Note: A default base url for the server has been set in the main.js file
*       so there is no need to define one here.
*
*       Axios will automatically append the path specified in any method calls
*       to the default base url, if it is set.
**************************************************************************************/

export default {

  login(user) {
    return axios.post('/login', user)   // path will be appended to default base url automatically
  },

  register(user) {
    return axios.post('/register', user) // path will be appended to default base url automatically
  }

}
