public class Plan1571768491051 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}


for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}


if ( StartServer("C") ) {
if ( StartServer("B") ) {
if ( StartServer("A") ) {
StartServer("A");
} else {
StartServer("A");
}

} else {
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

}

} else {
StartServer("A");
}


}
}
