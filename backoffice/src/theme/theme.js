import Colors from 'material-ui/lib/styles/colors';
import ColorManipulator from 'material-ui/lib/utils/color-manipulator';
import Spacing from 'material-ui/lib/styles/spacing';
import zIndex from 'material-ui/lib/styles/zIndex';

import CustomColors from './colors';

const CustomTheme =  {
  spacing: Spacing,
  zIndex: zIndex,
  fontFamily: 'Roboto, sans-serif',
  palette: {
        primary1Color: CustomColors.primary1,
        primary2Color: CustomColors.primary2,
        primary3Color: CustomColors.primary3,
        accent1Color: CustomColors.accent1,
        accent2Color: CustomColors.accent2,
        accent3Color: CustomColors.accent3,
        textColor: Colors.darkBlack,
        alternateTextColor: Colors.white,
        canvasColor: Colors.white,
        borderColor: Colors.grey300,
        disabledColor: ColorManipulator.fade(Colors.darkBlack, 0.3),
        pickerHeaderColor: Colors.cyan500,
  }
};

export default CustomTheme
