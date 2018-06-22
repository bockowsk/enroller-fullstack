<template>
  <div id="app">
    <h1>
      <img src="./assets/logo.svg" alt="Enroller" class="logo">
      System do zapisów na zajęcia
    </h1>
    <div v-if="authenticatedUsername">
      <h2>Witaj {{ authenticatedUsername }}!
        <a @click="logout()" class="float-right  button-outline button">Wyloguj</a>
      </h2>
      <meetings-page :username="authenticatedUsername"></meetings-page>
    </div>
    <div v-else>
      <button @click="registering = false" :class="registering ? 'button-outline' : ''">Loguję się</button>
      <button @click="registering = true" :class="!registering ? 'button-outline' : ''">Rejestruję się</button>
      <main-form @submit="registering ? register($event) : login($event)" :button-label="loginButtonLabel"></main-form>
    </div>
  </div>
</template>

<script>
	import Vue from "vue";
    import "milligram";
    import MainForm from "./MainForm";
    import MeetingsPage from "./meetings/MeetingsPage";

    export default {
        components: {MainForm, MeetingsPage, },
        data() {
            return {
                authenticatedUsername: "",
                registering: false,
            };
        },
        methods: {
            login(user) {
                this.$http.post('tokens', user)
                    .then(response => {
                         const token = response.body.token;
                         this.storeAuth(user.login, token);                        
                    })
                    .catch(() => this.failure('Logowanie nieudane.'));
            },
            storeAuth(username, token) {
                this.authenticatedUsername = username;
                Vue.http.headers.common.Authorization = 'Bearer ' + token;
                localStorage.setItem('username', username);
                localStorage.setItem('token', token);
            },
            register(user) {
                 this.$http.post('participants', user)
     				.then(response => {
         				// udało się
     				})
     				.catch(response => {
         				// nie udało sie     
     				});
            },
            logout() {
                this.authenticatedUsername = '';
                delete Vue.http.headers.common.Authorization;
                localStorage.clear();
            }
        },
        mounted() {
            const username = localStorage.getItem('username');
            const token = localStorage.getItem('token');
            if (username && token) {
                this.storeAuth(username, token);
                // if token expired or user has been deleted - logout!
                this.$http.get(`participants/${username}`).catch(() => this.logout());
            }
        },
        computed: {
        	loginButtonLabel() {
            	return this.registering ? 'Zarejestruj się' : 'Zaloguj się';
            }
        }
    };
</script>

<style>
  #app {
    max-width: 1000px;
    margin: 0 auto;
  }

  .logo {
    vertical-align: middle;
  }
</style>

