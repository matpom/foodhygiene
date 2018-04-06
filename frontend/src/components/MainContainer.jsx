import React from 'react';
import PropTypes from 'prop-types';

import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import Select from 'react-select';
import 'react-select/dist/react-select.css';
import './MainConatiner.css';
import {
  fetchLocalAuthorities,
  selectLocalAuthority,
} from '../actions/localAuthoritiesActions';
import { fetchEstablishmentsProfile } from '../actions/establishmentsProfileActions';
import EstablishmentsProfile from './establishmentsProfile/EstablishmentsProfile';
import NotificationBar from './notifications/NotificationBar';

class MainContainer extends React.PureComponent {
  componentDidMount() {
    this.props.fetchLocalAuthorities();
  }

  handleChange = (selectedOption) => {
    this.props.selectLocalAuthority(selectedOption);
    if (selectedOption) {
      this.props.fetchEstablishmentsProfile(selectedOption.id);
    }
  };

  render() {
    const {
      list: authoritiesOptions, pending: isLoading, selected,
    } = this.props.localAuthorities;
    return (
      <div className="container">
        <NotificationBar />
        <h1 id="title">Matts Foods Hygiene</h1>
        <Select
          name="form-field-name"
          value={selected ? selected.id : undefined}
          options={authoritiesOptions}
          valueKey="id"
          labelKey="name"
          isLoading={isLoading}
          onChange={this.handleChange}
        />
        {selected &&
          <EstablishmentsProfile
            localAuthorityName={selected.name}
            ratingDistribution={this.props.establishmentsProfile.ratingDistribution}
            isLoading={this.props.establishmentsProfile.pending}
          />
        }
      </div>
    );
  }
}

const localAuthorityShape = PropTypes.shape({
  id: PropTypes.number.isRequired,
  name: PropTypes.string.isRequired,
});

MainContainer.propTypes = {
  fetchLocalAuthorities: PropTypes.func.isRequired,
  fetchEstablishmentsProfile: PropTypes.func.isRequired,
  selectLocalAuthority: PropTypes.func.isRequired,
  localAuthorities: PropTypes.shape({
    pending: PropTypes.bool.isRequired,
    selected: localAuthorityShape,
    list: PropTypes.arrayOf(localAuthorityShape).isRequired,
  }).isRequired,
  establishmentsProfile: PropTypes.shape({
    pending: PropTypes.bool.isRequired,
    ratingDistribution: PropTypes.arrayOf(PropTypes.shape({
      rating: PropTypes.string.isRequired,
      percentage: PropTypes.number.isRequired,
    })).isRequired,
  }).isRequired,
};


function mapStateToProps(state) {
  return {
    localAuthorities: state.localAuthorities,
    establishmentsProfile: state.establishmentsProfile,
  };
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators({
    fetchLocalAuthorities,
    selectLocalAuthority,
    fetchEstablishmentsProfile,
  }, dispatch);
}

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(MainContainer);
