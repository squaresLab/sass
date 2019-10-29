public class Plan1571771430371 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("B");

}

if ( DecreaseDimmer("C") ) {
StartServer("B");
} else {
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
StartServer("B");
}

}

}


if ( StartServer("A") ) {
StartServer("B");
StartServer("C");

} else {
IncreaseTraffic("C");
}

StartServer("A");


}
}
