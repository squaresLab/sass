public class Plan1571774803013 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
if ( StartServer("A") ) {
IncreaseTraffic("C");
} else {
if ( StartServer("A") ) {
StartServer("A");
} else {
DecreaseTraffic("A");
}

}


}
}
