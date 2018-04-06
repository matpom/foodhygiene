import * as types from '../actions/constants';

const defaultState = {
  show: false,
  type: 'danger',
  text: '',
  dismissable: true,
};

export default function notificationBarReducer(state = defaultState, action) {
  switch (action.type) {
    case types.SHOW_NOTIFICATION_BAR:
      return action.payload;
    case types.HIDE_NOTIFICATION_BAR:
      return defaultState;
    default:
      return state;
  }
}
