import {
  FETCH_LOCAL_AUTHORITIES,
  SELECT_LOCAL_AUTHORITY,
} from './constants';
import * as api from '../api/api';
import { showFailureNotificationBar } from './notificationActions';

export function selectLocalAuthority(selected) {
  return {
    type: SELECT_LOCAL_AUTHORITY,
    selected,
  };
}

export function fetchLocalAuthorities() {
  return dispatch => dispatch({
    type: FETCH_LOCAL_AUTHORITIES,
    payload: api.fetchLocalAuthorities(),
  }).catch((error) => {
    dispatch(showFailureNotificationBar(error.response ?
      error.response.data.description : undefined));
  });
}
