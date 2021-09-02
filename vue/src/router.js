import { createRouter, createWebHistory } from 'vue-router'
//import App from './App.vue'

import Login from './components/Login'
import Ventas from './components/Ventas'
import Inventory from './components/Inventory'
import SignIn from './components/SignIn'
import Home from './components/Home'
import Welcome from './components/Welcome'

const routes = [{
    path: '/',
    name: 'root',
    component: Welcome
},
{
    path: '/login',
    name: "login",
    component: Login
},
{
    path: '/ventas',
    name: "ventas",
    component: Ventas
},
{
    path: '/signin',
    name: "signin",
    component: SignIn
},
{
    path: '/inventory',
    name: "inventory",
    component: Inventory
},
{
    path: '/home/:username',
    name: "home",
    component: Home
},



]

const router = createRouter({
    history: createWebHistory(''),
    routes
})

export default router