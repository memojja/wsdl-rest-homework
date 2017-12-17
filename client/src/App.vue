<template>
  <div id="app">
    <div id="wrapper" class="toggled">
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <router-link to="/">Kbucews Rest Client</router-link>
                </li>
                <li><router-link to="/users">Users</router-link></li>
                <li><router-link to="/questions">Questions</router-link></li>
                <li><router-link to="/competitions">Competitions</router-link></li>
            </ul>
        </div>
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <router-view></router-view>
            </div>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'app',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>
