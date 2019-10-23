public class Plan1571773959792 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("C");
StartServer("A");


}

DecreaseTraffic("A");

}

} else {
if ( StartServer("C") ) {
for (int i = 0; i < 5 ; i++) {
StartServer("B");
StartServer("C");
StartServer("A");


}

StartServer("C");

} else {
for (int i = 0; i < 3 ; i++) {
if (  ) {
DecreaseDimmer("A");
} else {
StartServer("A");
}

}

}

}

}
}
