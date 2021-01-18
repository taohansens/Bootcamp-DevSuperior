import './styles.scss';
import { ReactComponent as ArrowIcon } from '../../assets/images/arrow.svg'; 
const ButtonIcon = () => (
    <div className="d-flex">
        <button className="btn btn-primary btn-icon">
        <h5>Inicie sua busca</h5>
        </button>
        <div className="btn-icon-content">
            <ArrowIcon />
        </div>
    </div>
);

export default ButtonIcon;