import * as types from './constants';

export function showFailureNotificationBar(text) {
  return {
    type: types.SHOW_NOTIFICATION_BAR,
    payload: {
      show: true,
      type: 'danger',
      text: text || 'Unknown error has occurred',
    },
  };
}

export function hideNotificationBar() {
  return {
    type: types.HIDE_NOTIFICATION_BAR,
    payload: { show: false },
  };
}
