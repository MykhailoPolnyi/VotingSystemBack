package ua.lviv.iot.model.address;

import ua.lviv.iot.model.election.LocalityType;

public class AddressMatcher {
    public static Boolean compare(LocalityType level,
                                  Address userAddress,
                                  Address targetAddress) {
        if (level.equals(LocalityType.NATIONAL)) {
            return true;
        }
        var matcher = new StateMatcher();

        return matcher.checkIfMatch(level, userAddress, targetAddress);
    }

    private abstract static class AbstractMatcher {
        protected LocalityType level;

        public Boolean checkIfMatch(LocalityType level,
                                    Address userAddress,
                                    Address targetAddress) {
            Boolean isEqual = compare(userAddress, targetAddress);
            if (!isEqual) {
                return false;
            }
            AbstractMatcher nextMatcher = null;
            if (level.ordinal() < this.level.ordinal()) {
                nextMatcher = getNextMatcher();
            }

            if (nextMatcher != null) {
                isEqual = nextMatcher.checkIfMatch(level, userAddress, targetAddress);
            }
            return isEqual;
        }

        abstract protected AbstractMatcher getNextMatcher();

        abstract protected Boolean compare(Address userAddress, Address targetAddress);

    }

    private static class DistrictMatcher extends AbstractMatcher {
        public DistrictMatcher() {
            this.level = LocalityType.DISTRICT;
        }

        @Override
        protected Boolean compare(Address userAddress, Address targetAddress) {
            return userAddress.getDistrict().equals(targetAddress.getDistrict());
        }

        @Override
        protected AbstractMatcher getNextMatcher() {
            return null;
        }
    }

    private static class CityMatcher extends AbstractMatcher {
        public CityMatcher() {
            this.level = LocalityType.DISTRICT;
        }
        @Override
        protected Boolean compare(Address userAddress, Address targetAddress) {
            return userAddress.getCity().equals(targetAddress.getCity());
        }

        @Override
        protected AbstractMatcher getNextMatcher() {
            return new DistrictMatcher();
        }

    }

    private static class StateMatcher extends AbstractMatcher {
        public StateMatcher() {
            this.level = LocalityType.DISTRICT;
        }

        @Override
        protected Boolean compare(Address userAddress, Address targetAddress) {
            return userAddress.getState().equals(targetAddress.getState());
        }

        @Override
        protected AbstractMatcher getNextMatcher() {
            return new CityMatcher();
        }
    }
}
