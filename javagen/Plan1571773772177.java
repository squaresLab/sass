public class Plan1571773772177 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}

if ( IncreaseTraffic("B") ) {
DecreaseDimmer("B");
} else {
StartServer("C");
}


DecreaseTraffic("A");

StartServer("B");

StartServer("C");

} else {
IncreaseTraffic("B");
StartServer("A");

}

if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

} else {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

}


}
}
