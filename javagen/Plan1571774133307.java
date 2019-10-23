public class Plan1571774133307 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
if ( DecreaseDimmer("B") ) {
StartServer("C");
} else {
DecreaseTraffic("A");
}

for (int i = 0; i < 5 ; i++) {
StartServer("B");
}


if ( StartServer("C") ) {
StartServer("B");
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 5 ; i++) {
StartServer("A");
}

} else {

}


} else {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

} else {
StartServer("A");
}

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}




}
}
