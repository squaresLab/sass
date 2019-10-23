public class Plan1571767858721 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}

if ( StartServer("A") ) {
StartServer("A");
} else {
StartServer("A");
}


if ( StartServer("B") ) {
if ( DecreaseDimmer("B") ) {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("C");
}

} else {
StartServer("A");
}

} else {
StartServer("B");
}


}
}
