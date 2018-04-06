import axios from 'axios';
import config from '../config/Config';

export function fetchLocalAuthorities() {
  return axios.get(`${config().BASE_URL}/localAuthorities`);
}

export function fetchEstablishmentsProfile(localAuthorityId) {
  return axios.get(`${config().BASE_URL}/establishmentsProfile?localAuthorityId=${localAuthorityId}`);
}
