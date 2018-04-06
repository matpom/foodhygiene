import React from 'react';
import PropTypes from 'prop-types';
import { Alert } from 'react-bootstrap';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import { hideNotificationBar } from '../../actions/notificationActions';

class NotificationBar extends React.PureComponent {
  static TYPES = {
    success: 'success', danger: 'danger', warning: 'warning', info: 'info',
  };

  constructor(props) {
    super(props);
    this.hideNotificationBar = this.hideNotificationBar.bind(this);
  }

  hideNotificationBar() {
    this.props.hideNotificationBar();
  }

  render() {
    const {
      type, text, show,
    } = this.props;
    const iconType = (() => {
      switch (type) {
        case NotificationBar.TYPES.danger:
          return 'fa fa-exclamation-circle';
        case NotificationBar.TYPES.warning:
          return 'fa fa-exclamation-triangle';
        case NotificationBar.TYPES.success:
        default:
          return 'fa fa-check';
      }
    })();

    if (show) {
      return (
        <Alert id="notification" bsStyle={type} onDismiss={this.hideNotificationBar}>
          <p><i className={iconType} /> {text}</p>
        </Alert>
      );
    }
    return null;
  }
}

NotificationBar.propTypes = {
  hideNotificationBar: PropTypes.func.isRequired,
  show: PropTypes.bool.isRequired,
  type: PropTypes.oneOf(Object.keys(NotificationBar.TYPES).map(type => type)).isRequired,
  text: PropTypes.string.isRequired,
};

function mapStateToProps(state) {
  return {
    show: state.notificationBar.show,
    type: state.notificationBar.type,
    onDismiss: state.notificationBar.onDismiss,
    text: state.notificationBar.text,
  };
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators({
    hideNotificationBar,
  }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(NotificationBar);
