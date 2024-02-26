import { createStore as _createStore } from 'vuex';
import axios from 'axios';
import AuthService from '../services/AuthService';

const NOTIFICATION_TIMEOUT = 3000;

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
        user: currentUser || {},
      landmarks: [],
      itineraries: [],
      notification: null,
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      SET_USER_ITINERARIES(state, arrayOfItineraries) {
        state.itineraries = arrayOfItineraries;

      },
      SET_LANDMARKS(state, arrayOfLandmarks) {
        state.landmarks = arrayOfLandmarks;
      },
      SET_LANDMARK_CART(state, emptyArrayOfLandmarks) {
        state.landmarkCart = emptyArrayOfLandmarks
      },
      SET_NOTIFICATION(state, notification) {
        if (state.notification) {
          this.commit('CLEAR_NOTIFICATION');
        }
        if (typeof notification === 'string') {
          notification = {
            message: notification,
            type: 'error',
            timeout: NOTIFICATION_TIMEOUT
          }
        } else {
          notification.type = notification.type || 'error';
          notification.timeout = notification.timeout || NOTIFICATION_TIMEOUT;
        }
          state.notification = notification;
          notification.timer = window.setTimeout(() => {
            this.commit('CLEAR_NOTIFICATION');
        }, notification.timeout);
      },
      CLEAR_NOTIFICATION(state) {
        if (state.notification && state.notification.timer) {
          window.clearTimeout(state.notification.timer);
        }
        state.notification = null;
      },
    },
  });
  return store;
}
