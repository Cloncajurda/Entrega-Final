var Total = 0;
const StudentDiscount = 0.8;
const TraineeDiscount = 0.5;
const JuniorDiscount = 0.15;
const RegularPrice = 200;

function CalculatePrice()
{
    var Tickets = document.getElementById("TicketAmount").value;
    var temp = document.getElementById("PurchaseClass");
    var Category = temp.options[temp.selectedIndex].value;

    switch (Category)
    {
        case ("1"):
        {
            Discount = StudentDiscount;
            break;
        }
        case ("2"):
        {
            Discount = TraineeDiscount;
            break;
        }
        case ("3"):
        {
            Discount = JuniorDiscount;
            break;
        }
        default:
        {
            Discount = 0.0;
            break;
        }
    }

    Total = (RegularPrice - (RegularPrice * Discount)) * Tickets;
    document.getElementById("PriceShown").innerHTML = Total;
}

function ErasePrice()
{
    Total = 0;
    document.getElementById("PriceShown").innerHTML = Total;
    document.getElementById("TicketPurchase").reset();
}