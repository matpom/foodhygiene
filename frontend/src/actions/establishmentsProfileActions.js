import { FETCH_ESTABLISHMENTS_PROFILE } from './constants';
import * as api from '../api/api';
import { showFailureNotificationBar } from './notificationActions';

export function fetchEstablishmentsProfile(localAuthorityId) {
  return dispatch => dispatch({
    type: FETCH_ESTABLISHMENTS_PROFILE,
    payload: api.fetchEstablishmentsProfile(localAuthorityId),
  }).catch((error) => {
    dispatch(showFailureNotificationBar(error.response ?
      error.response.data.description : undefined));
  });
}
