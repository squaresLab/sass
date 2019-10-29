public class Plan1571769602846 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("A") ) {
DecreaseDimmer("A");
} else {
if ( StartServer("A") ) {
IncreaseTraffic("B");
} else {
if ( StartServer("A") ) {
StartServer("A");
} else {
IncreaseTraffic("A");
}

}

}

DecreaseTraffic("A");

}
}
