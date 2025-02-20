import axios from 'axios';

const API_URL = 'http://localhost:8082/';

export const login = (username, password) => {
    return axios.post(API_URL + 'auth/generateToken', { username, password });
};

export const register = (user) => {
    return axios.post(API_URL + 'auth/addNewUser', user);
};