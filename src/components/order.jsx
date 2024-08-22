import Table from 'react-bootstrap/Table';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../components/index.css'
import { Button } from 'react-bootstrap';

import ao from '../assets/aopolo.jpg'
import { Link, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';


import Modal from 'react-bootstrap/Modal';
import callAPI from '../axios';

function Order() {
    const navigate = useNavigate();
    const [orders, setOrders] = useState([])
    const [show, setShow] = useState(false);
    let order = {};

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const handlePay = (o) => {
        navigate(`/payment?id=${o}`)
    }
    useEffect(() => {
        fectAPI()
    }, [])
    const fectAPI = async () => {
        const response = await callAPI('/api/order', 'GET');
        setOrders(response)

    }
    console.log(orders)
    return (
        <div className='cart'>
            <h2>Đơn hàng của bạn</h2>

            <Table striped hover>
                <thead>
                    <tr>
                        <th>STT</th>
                        <th></th>
                        <th>Mã hoá đơn</th>
                        <th>Tên sản phẩm</th>
                        <th>giá</th>
                        <th>Trạng thái</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {orders?.map((o) => (
                        <tr key={o.id}>
                            <td>1</td>
                            <td><img src={ao} /></td>
                            <td>HD{o.id}</td>
                            <td>{o.productName}</td>
                            <td>{o.totalMoney}</td>
                            <td>{o.status == 1 ? 'Chưa thanh toán' : 'Đã thanh toán'}</td>
                            <td>
                                {o.status == 1 ? <Button onClick={() => { handlePay(o.id) }} variant="primary">Thanh toán</Button> :
                                    <div>
                                        <Button variant="primary" onClick={handleShow}>
                                            Xem thêm
                                        </Button>

                                        <Modal show={show} onHide={handleClose}>
                                            <Modal.Header closeButton>
                                                <Modal.Title>Nôi dung chuyển thanh toán</Modal.Title>
                                            </Modal.Header>
                                            <Modal.Body>
                                                <div>
                                                    <div></div>
                                                    <p>Tên ngân hàng : <b>{o?.bank?.nameBank}</b></p>
                                                    <p>Số tài khoản : <b>{o?.bank?.numberBank}</b></p>
                                                    <p>Tên người nhận : <b>{o?.bank?.name}</b></p>
                                                    <p>Số tiền : <b>{o.totalMoney}</b></p>
                                                    <p>Nội dung chuyển khoản : <b>VietBank</b></p>
                                                </div>

                                            </Modal.Body>
                                            <Modal.Footer>
                                                <Button variant="secondary" onClick={handleClose}>
                                                    Đóng
                                                </Button>

                                            </Modal.Footer>
                                        </Modal>
                                    </div>
                                }
                            </td>

                        </tr>
                    ))}
                </tbody>
            </Table>
        </div>
    );
}

export default Order;
