<template>
	<form @submit.prevent="enter()">
		<div v-if="register==false">
		    <label>Login</label>
		    <input type="text" v-model="user.login">
		    <label>Hasło</label>
		    <input type="password" v-model="user.password">
	 	</div>
	 	<div v-else>
	 		<label>Login</label>
		    <input type="text" v-model="user.login">
		    <label>Imie</label>
		    <input type="text" v-model="user.imie">
		    <label>Nazwisko</label>
		    <input type="text" v-model="user.nazwisko">
		    <label>Hasło</label>
		    <input type="password" v-model="user.password">
	 	
	 	</div>
    <button v-if="this.register==false" @click="swap()" type="submit">{{ labelOfTheButton1 }}</button>
    <button v-else @click="swap()" class="button button-outline" type="submit">{{ labelOfTheButton1 }}</button>
    
    <button v-if="this.register==true" @click="swap()" type="submit">{{ labelOfTheButton2 }}</button>
    <button v-else @click="swap()" class="button button-outline" type="submit">{{ labelOfTheButton2 }}</button>
	</form>
</template>

<script>
    export default {
        props: ["buttonLabel1"],
        data() {
            return {
                user: {},
                register: false,
            };
        },
        methods: {
            enter() {
                if (this.register==false) {
                	this.$emit("login", this.user);
                }
                else {
                	this.$emit("register", this.user);
                }
            },
            swap() {
				if (this.register==false) {
					this.register=true;
           		}
				else {
            		this.register=false;
            	}
			},
        },
        
        computed: {
            labelOfTheButton1() {
                return this.buttonLabel1 || 'Zaloguj się';
            },
            labelOfTheButton2() {
                return this.buttonLabel2 || 'Zarejestruj się';
            }
        }
    };
</script>
