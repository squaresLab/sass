public class Plan1571772699978 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {
IncreaseTraffic("B");
} else {
StartServer("A");
}

}

}


if ( StartServer("A") ) {
StartServer("B");
} else {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}

}


StartServer("C");

}
}
