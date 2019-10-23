public class Plan1571769325979 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("A") ) {
if ( DecreaseDimmer("C") ) {
StartServer("A");
} else {
StartServer("A");
}

} else {
if ( StartServer("A") ) {
StartServer("A");
} else {
IncreaseDimmer("A");
}

}

StartServer("B");

DecreaseTraffic("A");

}
}
