import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

import axios from 'axios'
Vue.prototype.$axios = axios

import App from './App.vue'
import Home from './components/Home.vue'
import User from './components/User.vue'
import Question from './components/Question.vue'
import Competition from './components/Competition.vue'

const router = new VueRouter({
  routes: [
    {path: '/', component: Home},
    {path: '/users', component: User},
    {path: '/questions', component: Question},
    {path: '/competitions', component: Competition},
  ],
  mode: 'history'
})

new Vue({
  el: '#app',
  render: h => h(App),
  router: router
})
