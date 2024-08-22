import Table from 'react-bootstrap/Table';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../components/index.css'
import { Button } from 'react-bootstrap';

import ao from '../assets/aopolo.jpg'
import { useNavigate } from 'react-router-dom';


function Cart() {

    const navigate = useNavigate();
    const handlePay = () => {
        navigate('/login');
        
    }
    return (
        <div className='cart'>
            <h2>Giỏ hàng</h2>

            <Table striped hover>
                <thead>
                    <tr>
                        <th></th>
                        <th>Têm sản phẩm</th>
                        <th>Giá</th>

                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><img src={ao} /></td>
                        <td>Áo</td>
                        <td>500000</td>
                    </tr>
                </tbody>
            </Table>
            <div>
                <Button onClick={handlePay} className='btn-pay'>Thanh toán</Button>
            </div>
        </div>
    );
}

export default Cart;