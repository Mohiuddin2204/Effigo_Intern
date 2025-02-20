import React, { useEffect, useState, useContext } from "react";
import axios from "axios";
import { styled } from "@mui/material/styles";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Button,
  Typography,
  Container,
} from "@mui/material";
import { AuthContext } from "../../context/AuthContext";
import { toast } from "react-toastify";

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${theme.palette.tableCellClasses?.head}`]: {
    backgroundColor: theme.palette.primary.main,
    color: theme.palette.common.white,
  },
  [`&.${theme.palette.tableCellClasses?.body}`]: {
    fontSize: 14,
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  "&:nth-of-type(odd)": {
    backgroundColor: theme.palette.action.hover,
  },
  "&:last-child td, &:last-child th": {
    border: 0,
  },
}));

const PaymentList = () => {
  const [payments, setPayments] = useState([]);
  const { authState } = useContext(AuthContext);

  useEffect(() => {
    fetchPayments();
  }, []);

  const fetchPayments = async () => {
    try {
      const response = await axios.get("http://localhost:8082/auth/admin/api/payments", {
        headers: { Authorization: `Bearer ${authState.token}` },
      });
      setPayments(response.data);
    } catch (error) {
      toast.error("Error fetching payments");
      console.error("Error fetching payments:", error);
    }
  };

  return (
    <Container sx={{ mt: 5 }}>
      <Typography variant="h4" align="center" gutterBottom>
        All Payments
      </Typography>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 700 }} aria-label="customized table">
          <TableHead>
            <TableRow>
              <StyledTableCell>#</StyledTableCell>
              <StyledTableCell>Payment ID</StyledTableCell>
              <StyledTableCell>Method</StyledTableCell>
              <StyledTableCell>Amount</StyledTableCell>
              <StyledTableCell>Status</StyledTableCell>
              <StyledTableCell>Order ID</StyledTableCell>
              <StyledTableCell>Username</StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {payments.length > 0 ? (
              payments.map((payment, index) => (
                <StyledTableRow key={payment.payId}>
                  <StyledTableCell>{index + 1}</StyledTableCell>
                  <StyledTableCell>{payment.payId}</StyledTableCell>
                  <StyledTableCell>{payment.payMethod}</StyledTableCell>
                  <StyledTableCell>${payment.payAmount}</StyledTableCell>
                  <StyledTableCell>{payment.payStatus}</StyledTableCell>
                  <StyledTableCell>{payment.order ? payment.order.orderId : "N/A"}</StyledTableCell>
                  <StyledTableCell>
                    {payment.order && payment.order.user ? payment.order.user.userName : "N/A"}
                  </StyledTableCell>
                </StyledTableRow>
              ))
            ) : (
              <StyledTableRow>
                <StyledTableCell colSpan={7} align="center">
                  No payments found
                </StyledTableCell>
              </StyledTableRow>
            )}
          </TableBody>
        </Table>
      </TableContainer>
      <Button variant="contained" color="primary" fullWidth sx={{ mt: 2 }} onClick={fetchPayments}>
        Refresh Payments
      </Button>
    </Container>
  );
};

export default PaymentList;
