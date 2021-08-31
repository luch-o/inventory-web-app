import { createRouter, createWebHistory } from 'vue-router'
import App from './App.vue'
import User from './components/User'
import UserAuth from './components/UserAuth'
import Account from './components/Account'
import Historial from './components/Historial'
import Transacction from './components/Transacction.vue'

const routes = [{
        path: '/',
        name: 'root',
        component: App
    },
    {
        path: '/user/auth',
        name: "user_auth",
        component: UserAuth
    },
    {
        path: '/user/:username',
        name: "user",
        component: User
    },
    {
        path: '/account/:username',
        name: "account",
        component: Account
    },
    {
        path: '/historial/:username',
        name: "historial",
        component: Historial
    },
    {
        path: '/transacction/:username',
        name: "transacction",
        component: Transacction
    }


]

const router = createRouter({
    history: createWebHistory(''),
    routes
})

export default router